package com.lunsir.subject.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lunsir.subject.domain.convert.SubjectCategoryDomainConverter;
import com.lunsir.subject.domain.convert.SubjectLabelDomainConverter;
import com.lunsir.subject.domain.entity.SubjectLabelBO;
import com.lunsir.subject.domain.service.SubjectCategoryDomainService;
import com.lunsir.subject.domain.entity.SubjectCategoryBO;
import com.lunsir.subject.infra.basic.entity.SubjectCategory;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;
import com.lunsir.subject.infra.basic.entity.SubjectMapping;
import com.lunsir.subject.infra.basic.service.SubjectCategoryService;
import com.lunsir.subject.infra.basic.service.SubjectLabelService;
import com.lunsir.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-03-01 20:43
 */
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    SubjectCategoryService subjectCategoryService;
    @Resource
    SubjectMappingService subjectMappingService;
    @Resource
    SubjectLabelService subjectLabelService;
    @Resource
    ThreadPoolExecutor labelThreadPool;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryDomainConverter.
                INSTANCE.
                convertBoToSubjectCategory(subjectCategoryBO);
        // add
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryDomainConverter.INSTANCE.convertBoToSubjectCategory(subjectCategoryBO);
        List<SubjectCategory> subjectCategorieList = subjectCategoryService.queryCategory(subjectCategory);
        //List<SubjectCategory> 转 list<BO>
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryDomainConverter.INSTANCE
                .convertToSubjectCategoryBOList(subjectCategorieList);
        // 拿到每一个分类下面的题目数
        for (SubjectCategoryBO categoryBO : subjectCategoryBOList) {
            Integer count = this.queryCountById(categoryBO.getId());
            categoryBO.setCount(count);
        }
        return subjectCategoryBOList;

    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        // BO 转 PO
        SubjectCategory subjectCategory = SubjectCategoryDomainConverter.INSTANCE.convertBoToSubjectCategory(subjectCategoryBO);
        return subjectCategoryService.updateById(subjectCategory);
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        return subjectCategoryService.removeById(subjectCategoryBO.getId());
    }

    @Override
    public Integer queryCountById(Long id) {
        return subjectCategoryService.queryCountById(id);
    }

    /**
     * 二期优化
     *
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO){
        // 查询当前大分类下面的所有分类以及标签
        LambdaQueryWrapper<SubjectCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubjectCategory::getParentId, subjectCategoryBO.getId());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.list(queryWrapper);
        // entityList to BoList
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryDomainConverter.INSTANCE.convertToSubjectCategoryBOList(subjectCategoryList);
        // 多线程CompletableFuture并发去拿数据
        // 通过查SubjectMapping表拿到各个分类下面的标签
        // 创建CompletableFutureList用来保存数据
        List<CompletableFuture<Map<Long,List<SubjectLabelBO>>>> completableFutureList =
                subjectCategoryBOList.stream()
                        .map(categoryBO-> CompletableFuture.supplyAsync(()->this.getSubjectLabelList(categoryBO.getId()),labelThreadPool))
                        .collect(Collectors.toList());
        // 拿到数据
        Map<Long,List<SubjectLabelBO>> map = new HashMap<>();
        completableFutureList.forEach(task->{
            Map<Long, List<SubjectLabelBO>> labelMap = null;
            try {
                labelMap = task.get();
                if (!CollectionUtils.isEmpty(labelMap)){
                    map.putAll(labelMap);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // 组装数据
        for (SubjectCategoryBO categoryBO : subjectCategoryBOList) {
            categoryBO.setSubjectLabelBOList(map.get(categoryBO.getId()));
        }
        return subjectCategoryBOList;

//        // 声明FutureTaskList
//        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new LinkedList<>();
//        // 线程池并发调用
//        subjectCategoryBOList.forEach((subjectCategory) -> {
//            // 声明一个 futureTask
//            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<Map<Long, List<SubjectLabelBO>>>(() -> {
//                return this.getSubjectLabelList(subjectCategory.getId());
//            });
//            // 通过线程池去提交(用多线程去进行异步查询)
//            labelThreadPool.submit(futureTask);
//
//            // 将futureTask放到futureTaskList,方便后续用到多线程查出来的结果（说白了就是保存结果）
//            futureTaskList.add(futureTask);
//        });
//        // 组装结果
//        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
//        for (FutureTask<Map<Long, List<SubjectLabelBO>>> task : futureTaskList) {
//            // 拿到多线程查出来的数据
//            Map<Long, List<SubjectLabelBO>> labelMap = null;
//            try {
//                labelMap = task.get();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            if (!CollectionUtils.isEmpty(labelMap)){
//                map.putAll(labelMap);
//            }
//        }
//
//        subjectCategoryBOList
//                .forEach(categoty->categoty.setSubjectLabelBOList(map.get(categoty.getId())));
//        return subjectCategoryBOList;
    }

    public Map<Long, List<SubjectLabelBO>> getSubjectLabelList(Long categoryId) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        LambdaQueryWrapper<SubjectMapping> mappingWrapper = new LambdaQueryWrapper<>();
        mappingWrapper.eq(SubjectMapping::getCategoryId, categoryId);
        List<SubjectMapping> subjectMappingList = subjectMappingService.list(mappingWrapper);
        // 从 subjectMappingList 拿到 所有的labelID
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId)
                .distinct()
                .collect(Collectors.toList());
        // 通过labelIdList去subjectLabel表中查数据
        List<SubjectLabel> labelList = subjectLabelService.listByIds(labelIdList);
        // entityList to BoList
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelDomainConverter.INSTANCE.SubjectLabelListToBOList(labelList);
        labelMap.put(categoryId, subjectLabelBOList);
        return labelMap;
    }
}
