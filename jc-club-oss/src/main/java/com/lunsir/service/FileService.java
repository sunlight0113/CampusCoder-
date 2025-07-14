package com.lunsir.service;

import com.lunsir.entity.FileInfo;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-16 21:05
 */
@Service
public class FileService{
    @Resource
    private StorageAdapter storageService;



    public void createBucket(String name) {
        storageService.createBucket(name);
    }


    public void uploadFile(MultipartFile multipartFile, String bucketName, String objectName){
        storageService.uploadFile(multipartFile,bucketName,objectName);
    }


    public List<String> getAllBucket() {
        return storageService.getAllBucket();
    }

    public List<FileInfo> getAllFilesOnBucket(String bucketName) {
        return storageService.getAllFilesOnBucket(bucketName);
    }

    public InputStream downloadFile(String bucketName, String objectName) {
        return storageService.downloadFile(bucketName,objectName);
    }

    public void deleteBucket(String bucketName) {
        storageService.deleteBucket(bucketName);
    }

    public void deleteFile(String bucketName, String objectName) {
        storageService.deleteFile(bucketName,objectName);
    }

    public String getUrl(String bucketName,String objectName){
        return storageService.getUrl(bucketName,objectName);
    }
}
