package com.candidate.controller;
import com.candidate.dto.ApiResponse;
import com.candidate.dto.OtpVerificationRequest;
import com.candidate.dto.SignupRequest;
import com.candidate.service.EmailServiceImpl;
import com.candidate.service.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(
        origins = {
                "*",
                "http://localhost:4200",
                "http://localhost:9876"
        })
@RestController
@RequestMapping("/api/v1/auth")
public class SignupController {

    private final UserService userService;
    private final EmailServiceImpl emailService;

    public SignupController(UserService userService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest signupRequest) {
        // Check if email is already registered
        if (userService.isEmailRegistered(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, "FAILED", "Email already registered", ""));
        }

        // Generate OTP and send it to the email
        String otp = userService.generateOtp(signupRequest.getEmail());
        emailService.sendPasswordByEmail(signupRequest.getEmail(), otp);

        // Return response indicating OTP was sent
        return ResponseEntity.ok(new ApiResponse(200, "SUCCESS", "OTP sent to email. Please verify.", ""));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOtp(@RequestBody OtpVerificationRequest otpRequest) {
        // Verify the OTP
        if (!userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, "FAILED", "Invalid OTP", ""));
        }

        // Activate the user (save name and email after OTP verification)
        userService.activateUser(otpRequest.getEmail());

        return ResponseEntity.ok(new ApiResponse(200, "SUCCESS", "OTP verified successfully. Account activated.", ""));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody OtpVerificationRequest loginRequest) {
        if (!userService.isEmailRegistered(loginRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, "FAILED", "User not registered. Please sign up first.", ""));
        }
        String otp = userService.generateOtp(loginRequest.getEmail());
        emailService.sendPasswordByEmail(loginRequest.getEmail(), otp);
        return ResponseEntity.ok(new ApiResponse(200, "SUCCESS", "OTP sent to your email for login verification.", ""));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@RequestBody OtpVerificationRequest logoutRequest) {
        // Invalidate user session or token (if using JWT or sessions)
        userService.logout(logoutRequest.getEmail());

        return ResponseEntity.ok(new ApiResponse(200, "SUCCESS", "User logged out successfully.", ""));
    }

}
