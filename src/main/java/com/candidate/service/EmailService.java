package com.candidate.service;

public interface EmailService {
    void sendPasswordByEmail(String email, String otp);
}
