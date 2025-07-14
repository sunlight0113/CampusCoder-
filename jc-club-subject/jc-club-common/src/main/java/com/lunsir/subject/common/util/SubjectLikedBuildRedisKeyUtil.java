package com.lunsir.subject.common.util;

/**
 * 获取点赞模块中的redis key
 * @author lunSir
 * @create 2024-10-14 16:19
 */
public class SubjectLikedBuildRedisKeyUtil {

    private static final String REDIS_HASH_KEY_TO_MYSQL = "subject_liked:hash";

    private static final String SUBJECT_LIKED_COUNT = "subject_liked:count.";

    /**
     * 获取用来判断user对subject是否点赞的key
     * @param userId
     * @param subjectId
     * @return
     */
    public static String buildUserLikedKey(String userId,String subjectId){
        return "subject_liked:" + subjectId + "." + userId;
    }

    /**
     * 获取redis中subject被多少人点赞的key
     * @param subjectId
     * @return
     */
    public static String buildSubjectLikedCountKey(String subjectId){
        return SUBJECT_LIKED_COUNT + subjectId;
    }

    /**
     * 获取redis中往mysql中刷数据的大的 hash key
     * @return
     */
    public static String buildPrimaryHashKey(){
        return REDIS_HASH_KEY_TO_MYSQL;
    }

    /**
     * 获取redis中往mysql中刷数据的大的 hash key下面的小的hash key
     * @param subjectId
     * @return
     */
    public static String buildSubjectLikedHashKey(String subjectId){
        return subjectId + "." + LoginUtil.getLoginId();
    }
}
