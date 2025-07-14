package com.lunsir.service.impl;

import com.lunsir.entity.FileInfo;
import com.lunsir.service.StorageAdapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-16 21:04
 */
public class AliyunStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String name) {

    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String bucketName, String objectName) {

    }

    @Override
    public List<String> getAllBucket() {
        return null;
    }

    @Override
    public List<FileInfo> getAllFilesOnBucket(String bucketName) {
        return null;
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucketName) {

    }

    @Override
    public void deleteFile(String bucketName, String objectName) {

    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return null;
    }
}
