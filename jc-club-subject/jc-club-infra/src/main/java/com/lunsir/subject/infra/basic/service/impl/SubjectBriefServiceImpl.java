package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectBriefDao;
import com.lunsir.subject.infra.basic.entity.SubjectBrief;
import com.lunsir.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;

/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:27:10
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl extends ServiceImpl<SubjectBriefDao, SubjectBrief> implements SubjectBriefService {

}

