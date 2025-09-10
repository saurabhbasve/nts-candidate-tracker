package com.candidate.controller;

import com.candidate.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(
        origins = {
                "*",
                "http://localhost:4200",
                "http://localhost:9876"
        })
@RestController
@RequestMapping("/Upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/resume/{subfolder}")
    public ResponseEntity<?> addDocumentToS3(@PathVariable String subfolder, @RequestParam("file") MultipartFile file) {
        try {
            String response = uploadService.uploadDocument(subfolder, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload document to S3");
        }
    }

}