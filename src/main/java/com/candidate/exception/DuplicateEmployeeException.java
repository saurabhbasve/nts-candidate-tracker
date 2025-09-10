package com.candidate.exception;

public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}