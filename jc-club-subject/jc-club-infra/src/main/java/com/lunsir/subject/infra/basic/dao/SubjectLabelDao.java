package com.lunsir.subject.infra.basic.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;

/**
 * 题目标签表(SubjectLabel)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-03 13:39:16
 */
public interface SubjectLabelDao extends BaseMapper<SubjectLabel> {

}

