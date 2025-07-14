package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectMappingDao;
import com.lunsir.subject.infra.basic.entity.SubjectMapping;
import com.lunsir.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:28:39
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl extends ServiceImpl<SubjectMappingDao, SubjectMapping> implements SubjectMappingService {

}

