package com.lunsir.subject.domain.job;

import com.lunsir.subject.common.util.SubjectLikedBuildRedisKeyUtil;
import com.lunsir.subject.domain.redis.RedisUtil;
import com.lunsir.subject.domain.service.SubjectLikedDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectLiked;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 同步点赞数据任务
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
@Slf4j
public class SyncLikedJob {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("syncLikedJobHandler")
    public void syncLikedJobHandler() throws Exception {
        XxlJobHelper.log("syncLikedJobHandler.start...");
        try {
            String hashPrimaryKey = SubjectLikedBuildRedisKeyUtil.buildPrimaryHashKey();

            Map<Object,Object> subjectLikedMap = redisUtil.getHashAndDelete(hashPrimaryKey);
            List<SubjectLiked> subjectLikedList = new LinkedList<>();
            subjectLikedMap.forEach((key,val)->{
                // key-> subjectId.userId
                String[] keyArr = key.toString().split("\\.");
                Long subjectId = Long.valueOf(keyArr[0]);
                String userId = keyArr[1];
                Integer states = Integer.valueOf(val.toString());
                SubjectLiked subjectLiked = new SubjectLiked();
                subjectLiked.setLikeUserId(userId);
                subjectLiked.setStatus(states);
                subjectLiked.setSubjectId(subjectId);
                subjectLikedList.add(subjectLiked);
            });
            subjectLikedDomainService.syncBatchInsertLiked(subjectLikedList);

        }catch (Exception e){
            XxlJobHelper.log("syncLikedJobHandler.error:{}",e.getMessage());
        }
    }

}
