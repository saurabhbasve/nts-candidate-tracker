package com.candidate.service;

public interface UserService {
    boolean isEmailRegistered(String email);
    String generateOtp(String email);
    boolean verifyOtp(String email, String otp);
    void activateUser(String email);  // After OTP verification, save user
    void logout(String email);
}

