package com.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class UploadService {

    @Value("${aws.bucketName}")
    private String bucketName;

    @Autowired
    S3Client s3Client;

    public String uploadDocument(String subfolder, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String key = "/" + subfolder + "/" + fileName;
            s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key)
                            .contentType("application/octet-stream").build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return "Document added to S3 bucket in folder: "+ "/" + subfolder;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add document to S3 bucket", e);
        }
    }
}


