package com.lunsir.service.impl;

import com.lunsir.entity.FileInfo;
import com.lunsir.service.StorageAdapter;
import com.lunsir.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-12 13:30
 */
//@Service 在StorageConfig类中做了条件注入，这里就不用注入了
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Value("${minio.url}")
    private String url;


    @Override
    @SneakyThrows
    public void createBucket(String name) {
        minioUtil.createBucket(name);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile multipartFile, String bucketName, String objectName){
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            minioUtil.uploadFile(multipartFile.getInputStream(), bucketName, objectName + "/" + multipartFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(multipartFile.getInputStream(), bucketName, multipartFile.getOriginalFilename());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFilesOnBucket(String bucketName) {
        return minioUtil.getAllFilesOnBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String objectName) {
        return minioUtil.downloadFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucketName) {
        minioUtil.deleteBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucketName, String objectName) {
        minioUtil.deleteFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucket, String objectName) {
        return url + "/" + bucket + "/" + objectName;
    }

}
