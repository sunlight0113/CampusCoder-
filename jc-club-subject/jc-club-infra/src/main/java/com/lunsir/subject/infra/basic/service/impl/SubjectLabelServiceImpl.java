package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectLabelDao;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;
import com.lunsir.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

/**
 * 题目标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 13:39:16
 */
@Service("subjectLabelService")
public class SubjectLabelServiceImpl extends ServiceImpl<SubjectLabelDao, SubjectLabel> implements SubjectLabelService {

}

