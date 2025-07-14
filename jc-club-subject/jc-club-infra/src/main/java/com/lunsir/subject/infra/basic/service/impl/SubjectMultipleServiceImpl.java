package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectMultipleDao;
import com.lunsir.subject.infra.basic.entity.SubjectMultiple;
import com.lunsir.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

/**
 * 多选题信息表(SubjectMultiple)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:28:58
 */
@Service("subjectMultipleService")
public class SubjectMultipleServiceImpl extends ServiceImpl<SubjectMultipleDao, SubjectMultiple> implements SubjectMultipleService {

}

