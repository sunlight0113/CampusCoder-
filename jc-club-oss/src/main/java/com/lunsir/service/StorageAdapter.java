package com.lunsir.service;

import com.lunsir.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-12 13:28
 */
public interface StorageAdapter {
    /**
     * 创建bucket桶
     */
    void createBucket(String name);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile multipartFile, String bucketName, String objectName);

    /**
     * 列出所有的桶
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶的所有文件
     */
    List<FileInfo> getAllFilesOnBucket(String bucketName);

    /**
     * 下载文件
     */
    InputStream downloadFile(String bucketName, String objectName);

    /**
     * 删除一个bucket
     */
    void deleteBucket(String bucketName);

    /**
     * 删除一个文件
     */
    void deleteFile(String bucketName, String objectName);

    /**
     * 获取文件的url地址
     */
    String getUrl(String bucket, String objectName);
}
