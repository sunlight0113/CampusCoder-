package com.lunsir.subject.domain.factory.handler.subject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.Preconditions;
import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;
import com.lunsir.subject.infra.basic.entity.SubjectBrief;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.service.SubjectBriefService;
import com.lunsir.subject.infra.basic.service.SubjectRadioService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 简答类
 * 要想从Spring容器中注入类，那么当前类也必须被Spring管理
 * @author lunSir
 * @create 2024-03-03 17:35
 */
@Component
public class Brief implements SubjectTypeAdapter{
    @Resource
    private SubjectBriefService subjectBriefService ;

    @Override
    public SubjectTypeEnum isSupported() {
        return SubjectTypeEnum.BRIEF;
    }

    @Override
    @Transactional
    public Boolean add(SubjectBO subjectBO) {
        Preconditions.checkArgument(StringUtils.isNotBlank(subjectBO.getSubjectAnswer()),"简答题答案不能为空");
        Preconditions.checkNotNull(subjectBO.getId(),"subjectId不能为空");
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectBO.getId());
        subjectBrief.setSubjectAnswer(subjectBO.getSubjectAnswer());
        return subjectBriefService.save(subjectBrief);
    }

    @Override
    public SubjectOptionBo query(Long subjectId) {
        LambdaQueryWrapper<SubjectBrief> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubjectBrief::getSubjectId,subjectId);
        SubjectBrief subjectBrief = subjectBriefService.getOne(queryWrapper);
        SubjectOptionBo subjectOptionBo = new SubjectOptionBo();
        subjectOptionBo.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBo;
    }
}
