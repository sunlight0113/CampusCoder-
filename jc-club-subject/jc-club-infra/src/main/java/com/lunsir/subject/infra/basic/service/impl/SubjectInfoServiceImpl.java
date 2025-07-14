package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectInfoDao;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:27:46
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl extends ServiceImpl<SubjectInfoDao, SubjectInfo> implements SubjectInfoService {

    @Override
    public int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId) {
        return this.baseMapper.countByCondition(subjectInfo,categoryId,labelId);
    }

    @Override
    public List<SubjectInfo> queryByPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize) {
        return this.baseMapper.queryByPage(subjectInfo, categoryId, labelId, start, pageSize);
    }
}

