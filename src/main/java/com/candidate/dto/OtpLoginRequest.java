package com.candidate.dto;

public class OtpLoginRequest {
    private String email;

    // Constructor
    public OtpLoginRequest() {}

    public OtpLoginRequest(String email) {
        this.email = email;
    }

    // Getter and Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
