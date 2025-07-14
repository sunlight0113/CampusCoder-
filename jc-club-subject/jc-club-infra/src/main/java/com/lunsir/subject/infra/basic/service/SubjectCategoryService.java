package com.lunsir.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lunsir.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务接口
 *
 * @author makejava
 * @since 2024-02-29 21:49:09
 */
public interface SubjectCategoryService extends IService<SubjectCategory>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory insert(SubjectCategory subjectCategory);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory update(SubjectCategory subjectCategory);


    List<SubjectCategory> queryCategory(SubjectCategory subjectCategory);

    Integer queryCountById(Long id);
}
