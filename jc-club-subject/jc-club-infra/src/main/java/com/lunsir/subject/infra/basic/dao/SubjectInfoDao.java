package com.lunsir.subject.infra.basic.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;

/**
 * 题目信息表(SubjectInfo)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-03 15:27:46
 */
public interface SubjectInfoDao extends BaseMapper<SubjectInfo> {


    int countByCondition(@Param("subjectInfo") SubjectInfo subjectInfo,
                         @Param("categoryId") Long categoryId,
                         @Param("labelId") Long labelId);

    List<SubjectInfo> queryByPage(@Param("subjectInfo") SubjectInfo subjectInfo,
                                  @Param("categoryId") Long categoryId,
                                  @Param("labelId") Long labelId,
                                  @Param("start") int start,
                                  @Param("pageSize") Integer pageSize);
}

