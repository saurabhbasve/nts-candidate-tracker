package com.candidate.dto;

public class ApiResponse {
    private int status;
    private String message;
    private String description;
    private Object data;

    public ApiResponse(int status, String message, String description, Object data) {
        this.status = status;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
