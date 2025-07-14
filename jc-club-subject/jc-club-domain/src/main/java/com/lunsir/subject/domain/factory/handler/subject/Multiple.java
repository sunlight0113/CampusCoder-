package com.lunsir.subject.domain.factory.handler.subject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.domain.convert.SubjectDomainConverter;
import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;
import com.lunsir.subject.infra.basic.entity.SubjectJudge;
import com.lunsir.subject.infra.basic.entity.SubjectMultiple;
import com.lunsir.subject.infra.basic.service.SubjectMultipleService;
import com.lunsir.subject.infra.basic.service.SubjectRadioService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选类
 * 要想从Spring容器中注入类，那么当前类也必须被Spring管理
 * @author lunSir
 * @create 2024-03-03 17:35
 */
@Component
public class Multiple implements SubjectTypeAdapter{
    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectTypeEnum isSupported() {
        return SubjectTypeEnum.MULTIPLE;
    }

    @Transactional
    @Override
    public Boolean add(SubjectBO subjectBO) {
        // 判断OptionList是否为空
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectBO.getOptionList()),"题目信息不能为空");
        Preconditions.checkNotNull(subjectBO.getId(),"subjectId不能为空");

        List<SubjectMultiple> subjectMultipleList = Lists.newArrayList();
        subjectBO.getOptionList().forEach(e->{
            Preconditions.checkNotNull(e.getOptionType(),"选项类型不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(e.getOptionContent()),"选项内容不能为空");
            Preconditions.checkNotNull(e.getIsCorrect(),"isCorrect不能为空");
            SubjectMultiple subjectMultiple = new SubjectMultiple();
            subjectMultiple.setSubjectId(subjectBO.getId());
            subjectMultiple.setOptionType(e.getOptionType());
            subjectMultiple.setIsCorrect(e.getIsCorrect());
            subjectMultiple.setOptionContent(e.getOptionContent());
            subjectMultipleList.add(subjectMultiple);
        });
        return subjectMultipleService.saveBatch(subjectMultipleList);
    }

    @Override
    public SubjectOptionBo query(Long subjectId) {
        LambdaQueryWrapper<SubjectMultiple> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubjectMultiple::getSubjectId,subjectId);
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.list(queryWrapper);
        // subjectJMultipleList 转 SubjectOptionBo
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectDomainConverter.INSTANCE.subjectMultipleListToSubjectAnswerBOList(subjectMultipleList);
        SubjectOptionBo subjectOptionBo = new SubjectOptionBo();
        subjectOptionBo.setOptionList(subjectAnswerBOList);
        return subjectOptionBo;
    }
}
