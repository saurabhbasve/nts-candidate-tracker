package com.candidate.service;

import com.candidate.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordByEmail(String email, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");  // ✅ Multipart enabled

            // ✅ Explicitly set the "From" address (should match spring.mail.username)
            helper.setFrom("saurabh.basve@neutrinotechlabs.com");
            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText("<p>Your OTP code is: <b>" + otp + "</b></p>", true);  // ✅ HTML Support

            mailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
