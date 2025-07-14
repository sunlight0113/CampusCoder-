package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectLikedDao;
import com.lunsir.subject.infra.basic.entity.SubjectLiked;
import com.lunsir.subject.infra.basic.service.SubjectLikedService;
import org.springframework.stereotype.Service;

/**
 * 题目点赞表(SubjectLiked)表服务实现类
 *
 * @author makejava
 * @since 2024-10-14 14:01:30
 */
@Service("subjectLikedService")
public class SubjectLikedServiceImpl extends ServiceImpl<SubjectLikedDao, SubjectLiked> implements SubjectLikedService {

}

