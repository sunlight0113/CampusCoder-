package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lunsir.subject.common.entity.PageResult;
import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.infra.basic.entity.EsSubjectFields;
import com.lunsir.subject.infra.basic.entity.SubjectInfoEs;
import com.lunsir.subject.infra.basic.es.EsIndexInfo;
import com.lunsir.subject.infra.basic.es.EsRestClient;
import com.lunsir.subject.infra.basic.es.EsSearchRequest;
import com.lunsir.subject.infra.basic.es.EsSourceData;
import com.lunsir.subject.infra.basic.service.SubjectEsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author lunSir
 * @create 2024-10-12 18:07
 */
@Service
@Slf4j
public class SubjectEsServiceImpl implements SubjectEsService {


    @Override
    public boolean insert(SubjectInfoEs subjectInfoEs) {
        EsSourceData esSourceData = new EsSourceData();
        Map<String, Object> data = esInfoConvert2Map(subjectInfoEs);
        esSourceData.setData(data);
        esSourceData.setDocId(subjectInfoEs.getDocId().toString());
        EsRestClient.insertDoc(getEsIndexInfo(),esSourceData);
        return false;
    }

    @Override
    public PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs req) {
        PageResult<SubjectInfoEs> pageResult = new PageResult<>();
        EsSearchRequest esSearchRequest = createSearchListQuery(req);
        SearchResponse searchResponse = EsRestClient.searchWithTermQuery(getEsIndexInfo(), esSearchRequest);

        List<SubjectInfoEs> subjectInfoEsList = new LinkedList<>();
        SearchHits searchHits = searchResponse.getHits();
        if (searchHits == null || searchHits.getHits() == null) {
            pageResult.setPageNo(req.getPageNo());
            pageResult.setPageSize(req.getPageSize());
            pageResult.setResult(subjectInfoEsList);
            pageResult.setTotal(0);
            return pageResult;
        }
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            SubjectInfoEs subjectInfoEs = convertResult(hit);
            if (Objects.nonNull(subjectInfoEs)) {
                subjectInfoEsList.add(subjectInfoEs);
            }
        }
        pageResult.setPageNo(req.getPageNo());
        pageResult.setPageSize(req.getPageSize());
        pageResult.setResult(subjectInfoEsList);
        pageResult.setTotal(Long.valueOf(searchHits.getTotalHits().value).intValue());
        return pageResult;
    }

    private EsIndexInfo getEsIndexInfo() {
        EsIndexInfo esIndexInfo = new EsIndexInfo();
        esIndexInfo.setClusterName("6fcc4cba07ca");
        esIndexInfo.setIndexName("subject_index");
        return esIndexInfo;
    }

    private SubjectInfoEs convertResult(SearchHit hit) {
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        if (CollectionUtils.isEmpty(sourceAsMap)) {
            return null;
        }
        SubjectInfoEs result = new SubjectInfoEs();
        result.setSubjectId(MapUtils.getLong(sourceAsMap, EsSubjectFields.SUBJECT_ID));
        result.setSubjectName(MapUtils.getString(sourceAsMap, EsSubjectFields.SUBJECT_NAME));

        result.setSubjectAnswer(MapUtils.getString(sourceAsMap, EsSubjectFields.SUBJECT_ANSWER));

        result.setDocId(MapUtils.getLong(sourceAsMap, EsSubjectFields.DOC_ID));
        result.setSubjectType(MapUtils.getInteger(sourceAsMap, EsSubjectFields.SUBJECT_TYPE));
        result.setScore(new BigDecimal(String.valueOf(hit.getScore())).multiply(new BigDecimal("100.00")
                .setScale(2, RoundingMode.HALF_UP)));

        //处理name的高亮
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        HighlightField subjectNameField = highlightFields.get(EsSubjectFields.SUBJECT_NAME);
        if(Objects.nonNull(subjectNameField)){
            Text[] fragments = subjectNameField.getFragments();
            StringBuilder subjectNameBuilder = new StringBuilder();
            for (Text fragment : fragments) {
                subjectNameBuilder.append(fragment);
            }
            result.setSubjectName(subjectNameBuilder.toString());
        }

        //处理答案高亮
        HighlightField subjectAnswerField = highlightFields.get(EsSubjectFields.SUBJECT_ANSWER);
        if(Objects.nonNull(subjectAnswerField)){
            Text[] fragments = subjectAnswerField.getFragments();
            StringBuilder subjectAnswerBuilder = new StringBuilder();
            for (Text fragment : fragments) {
                subjectAnswerBuilder.append(fragment);
            }
            result.setSubjectAnswer(subjectAnswerBuilder.toString());
        }

        return result;
    }

    private EsSearchRequest createSearchListQuery(SubjectInfoEs req) {
        EsSearchRequest esSearchRequest = new EsSearchRequest();
        BoolQueryBuilder bq = new BoolQueryBuilder();
        MatchQueryBuilder subjectNameQueryBuilder =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_NAME, req.getKeyWord());

        bq.should(subjectNameQueryBuilder);
        // 提高权重，这个查询条件查询出来的内容放到前面
        subjectNameQueryBuilder.boost(2);

        MatchQueryBuilder subjectAnswerQueryBuilder =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_ANSWER, req.getKeyWord());
        bq.should(subjectAnswerQueryBuilder);

        MatchQueryBuilder subjectTypeQueryBuilder =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_TYPE, SubjectTypeEnum.BRIEF.getType());
        bq.must(subjectTypeQueryBuilder);
        bq.minimumShouldMatch(1);

        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
        highlightBuilder.preTags("<span style = \"color:red\">");
        highlightBuilder.postTags("</span>");

        esSearchRequest.setBq(bq);
        esSearchRequest.setHighlightBuilder(highlightBuilder);
        esSearchRequest.setFields(EsSubjectFields.FIELD_QUERY);
        esSearchRequest.setFrom((req.getPageNo() - 1) * req.getPageSize());
        esSearchRequest.setSize(req.getPageSize());
        esSearchRequest.setNeedScroll(false);
        return esSearchRequest;
    }

    private Map<String, Object> esInfoConvert2Map(SubjectInfoEs subjectInfoEs) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(EsSubjectFields.DOC_ID,subjectInfoEs.getDocId());
        data.put(EsSubjectFields.SUBJECT_ID,subjectInfoEs.getSubjectId());
        data.put(EsSubjectFields.SUBJECT_NAME,subjectInfoEs.getSubjectName());
        data.put(EsSubjectFields.SUBJECT_ANSWER,subjectInfoEs.getSubjectAnswer());
        data.put(EsSubjectFields.SUBJECT_TYPE,subjectInfoEs.getSubjectType());
        data.put(EsSubjectFields.CREATE_USER,subjectInfoEs.getCreateUser());
        data.put(EsSubjectFields.CREATE_TIME,subjectInfoEs.getCreateTime());
        return data;
    }


}
