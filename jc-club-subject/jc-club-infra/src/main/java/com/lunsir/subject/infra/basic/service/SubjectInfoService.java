package com.lunsir.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-03-03 15:27:46
 */
public interface SubjectInfoService extends IService<SubjectInfo> {

    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    List<SubjectInfo> queryByPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);

}

