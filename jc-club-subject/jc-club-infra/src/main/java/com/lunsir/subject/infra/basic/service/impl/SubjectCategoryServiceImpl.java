package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.common.enums.DeletedFlagEnum;
import com.lunsir.subject.infra.basic.entity.SubjectCategory;
import com.lunsir.subject.infra.basic.dao.SubjectCategoryDao;
import com.lunsir.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-02-29 21:49:10
 */
@Service("subjectCategoryService")
public class SubjectCategoryServiceImpl extends ServiceImpl<SubjectCategoryDao,SubjectCategory> implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        // add
        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory update(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.update(subjectCategory);
        return this.queryById(subjectCategory.getId());
    }


    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        // 要查询is_deleted等于0的
        subjectCategory.setIsDeleted(DeletedFlagEnum.NOT_DELETED.getCode());
        return this.subjectCategoryDao.queryCategory(subjectCategory);
    }

    @Override
    public Integer queryCountById(Long id) {
        return subjectCategoryDao.queryCountById(id);
    }
}
