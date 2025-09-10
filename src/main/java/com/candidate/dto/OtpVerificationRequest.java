package com.candidate.dto;

public class OtpVerificationRequest {
    private String email;
    private String otp;

    // Constructors
    public OtpVerificationRequest() {}

    public OtpVerificationRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for otp
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
