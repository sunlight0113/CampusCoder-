package com.lunsir.application.subject.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lunsir.application.subject.convert.SubjectLikedDTOConvert;
import com.lunsir.application.subject.dto.SubjectLikedDTO;
import com.lunsir.subject.common.entity.Result;
import com.lunsir.subject.domain.entity.SubjectLikedBO;
import com.lunsir.subject.domain.service.SubjectLikedDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectLiked;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-10-14 14:11
 */
@RestController
@RequestMapping("/subject/subjectLiked")
@Slf4j
public class SubjectLikedController {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 点赞 or 取消点赞
     * @param subjectLikedDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> likeOrUnlike(@RequestBody SubjectLikedDTO subjectLikedDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("点赞 or 取消点赞：SubjectLikedController.likeOrUnlike.subjectLikedDTO:{}", JSON.toJSONString(subjectLikedDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(),"点赞题目不能为空！");
            Preconditions.checkNotNull(subjectLikedDTO.getStatus(),"点赞状态不能为空！");
            // DTO to BO
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConvert.INSTANCE.dtoToBo(subjectLikedDTO);
            // domainService
            Boolean flag = subjectLikedDomainService.likeOrUnlike(subjectLikedBO);
            return Result.ok(flag);
        }catch (Exception e){
            log.error("点赞 or 取消点赞：SubjectLikedController.likeOrUnlike.errorMsg:{}",e.getMessage());
            return Result.fail(false, e.getMessage());
        }
    }

    /**
     * 我的点赞列表
     * @param subjectLikedDTO
     * @return
     */
    @PostMapping("/getSubjectLikedPage")
    public Result<Page<SubjectLikedDTO>> getSubjectLikedPage(@RequestBody SubjectLikedDTO subjectLikedDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("我的点赞列表：SubjectLikedController.getSubjectLikedPage.subjectLikedDTO:{}", JSON.toJSONString(subjectLikedDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(subjectLikedDTO.getPageNo(),"页数不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getPageSize(),"每页显示多少条数据不能为空！");
            // DTO to BO
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConvert.INSTANCE.dtoToBo(subjectLikedDTO);
            // domainService
            Page<SubjectLikedBO> boPage = subjectLikedDomainService.getSubjectLikedPage(subjectLikedBO);
            List<SubjectLikedBO> records = boPage.getRecords();
            List<SubjectLikedDTO> subjectLikedDTOList = SubjectLikedDTOConvert.INSTANCE.boListToDtoList(records);
            Page<SubjectLikedDTO> dtoPage = new Page<>();
            dtoPage.setCurrent(boPage.getCurrent());
            dtoPage.setTotal(boPage.getTotal());
            dtoPage.setSize(boPage.getSize());
            dtoPage.setRecords(subjectLikedDTOList);
            return Result.ok(dtoPage);
        }catch (Exception e){
            log.error("我的点赞列表：SubjectLikedController.getSubjectLikedPage.errorMsg:{}",e.getMessage());
            return Result.fail(null, e.getMessage());
        }
    }

}
