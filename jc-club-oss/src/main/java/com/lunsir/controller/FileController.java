package com.lunsir.controller;

import com.lunsir.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-12 13:12
 */
@RestController
@RefreshScope
public class FileController {
    @Resource
    private FileService fileService;

    @Resource
    private MinioClient minioClient;

    @Value(value = "${lunsir.storage.type}")
    private String type;

    @GetMapping("/test")
    public String test() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return type;
    }

    /**
     * 获取上传的文件的url
     * @param bucketName
     * @param objectName
     * @return
     */
    @RequestMapping("/getUrl")
    public String getUrl(@RequestParam("bucketName") String bucketName,@RequestParam("objectName") String objectName){
        return fileService.getUrl(bucketName,objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, String bucketName, String objectName) throws Exception {
        fileService.uploadFile(file,bucketName,objectName);
        return fileService.getUrl(bucketName,objectName + "/" + file.getOriginalFilename());
    }

}
