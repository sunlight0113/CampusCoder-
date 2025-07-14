package com.lunsir.subject.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lunsir.subject.domain.entity.SubjectLikedBO;
import com.lunsir.subject.infra.basic.entity.SubjectLiked;

import java.util.List;

/**
 *
 * @author lunsir
 * @since 2024-10-14 14:01:30
 */
public interface SubjectLikedDomainService{

    Boolean likeOrUnlike(SubjectLikedBO subjectLikedBO);

    Boolean isliked(Long subjectId, String userId);

    Integer getSubjectLikedCount(Long subjectId);

    void syncBatchInsertLiked(List<SubjectLiked> subjectLikedList);

    Page<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);
}

