package com.lunsir.subject.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lunsir.subject.domain.convert.SubjectLabelDomainConverter;
import com.lunsir.subject.domain.entity.SubjectLabelBO;
import com.lunsir.subject.domain.service.SubjectLabelDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;
import com.lunsir.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 13:56
 */
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    /**
     * 插入分类标签
     * @param subjectLabelBO
     * @return
     */
    public Boolean addSubjectLabel(SubjectLabelBO subjectLabelBO){
        // BO 转 Entity
        SubjectLabel subjectLabel = SubjectLabelDomainConverter.INSTANCE.BOToSubjectLabel(subjectLabelBO);
        return subjectLabelService.save(subjectLabel);
    }

    @Override
    public Boolean updateSubjectLabel(SubjectLabelBO subjectLabelBO) {
        // BO 转 Entity
        SubjectLabel subjectLabel = SubjectLabelDomainConverter.INSTANCE.BOToSubjectLabel(subjectLabelBO);
        return subjectLabelService.updateById(subjectLabel);
    }

    @Override
    public Boolean deleteSubjectLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelDomainConverter.INSTANCE.BOToSubjectLabel(subjectLabelBO);
        return subjectLabelService.removeById(subjectLabel.getId());
    }

    @Override
    public List<SubjectLabelBO> querySubjectLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        // BO 转 entity
        SubjectLabel subjectLabel = SubjectLabelDomainConverter.INSTANCE.BOToSubjectLabel(subjectLabelBO);
        LambdaQueryWrapper<SubjectLabel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SubjectLabel::getCategoryId, subjectLabel.getCategoryId());
        List<SubjectLabel> labelList = subjectLabelService.list(lambdaQueryWrapper);
        // EntityList转BOList
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelDomainConverter.INSTANCE.SubjectLabelListToBOList(labelList);
        return subjectLabelBOList;
    }

}
