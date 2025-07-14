package com.lunsir.subject.infra.basic.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.lunsir.subject.infra.basic.entity.SubjectMapping;

/**
 * 题目分类关系表(SubjectMapping)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-03 15:28:39
 */
public interface SubjectMappingDao extends BaseMapper<SubjectMapping> {

}

