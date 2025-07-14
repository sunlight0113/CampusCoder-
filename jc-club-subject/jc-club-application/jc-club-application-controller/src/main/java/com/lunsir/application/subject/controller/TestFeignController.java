package com.lunsir.application.subject.controller;

import com.lunsir.subject.infra.basic.entity.SubjectLiked;
import com.lunsir.subject.infra.basic.service.SubjectLikedService;
import com.lunsir.subject.infra.rpc.UserRPC;
import com.lunsir.subject.infra.rpc.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lunSir
 * @create 2024-10-11 10:43
 */
@RestController
@RequestMapping("/subject")
public class TestFeignController {

    @Resource
    private UserRPC userRPC;

    @Resource
    private SubjectLikedService subjectLikedService;

    @GetMapping("/testFeign")
    public UserInfo testFeign(){
        return userRPC.getUserInfo("lunsir");
    }
    @GetMapping("/testSaveBatch")
    public void testSaveBatch(){
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setSubjectId(100L);
        subjectLiked.setLikeUserId("sdawdsadad");
        subjectLiked.setStatus(0);
        ArrayList<SubjectLiked> subjectLikeds = new ArrayList<>();
        subjectLikeds.add(subjectLiked);
        subjectLikedService.save(subjectLiked);

    }

}
