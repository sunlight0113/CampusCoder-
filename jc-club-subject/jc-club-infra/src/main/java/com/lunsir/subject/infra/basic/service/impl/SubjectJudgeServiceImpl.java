package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectJudgeDao;
import com.lunsir.subject.infra.basic.entity.SubjectJudge;
import com.lunsir.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Service;

/**
 * 判断题(SubjectJudge)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:28:12
 */
@Service("subjectJudgeService")
public class SubjectJudgeServiceImpl extends ServiceImpl<SubjectJudgeDao, SubjectJudge> implements SubjectJudgeService {

}

