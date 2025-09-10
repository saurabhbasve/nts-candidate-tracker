package com.candidate.service;

import com.candidate.model.User;
import com.candidate.repository.UserRepository;
import com.candidate.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Map<String, String> otpStorage = new HashMap<>();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStorage.put(email, otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(otp);
    }

    @Override
    @Transactional
    public void activateUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            // If user doesn't exist in DB, create a new user and save it
            String defaultPassword = new BCryptPasswordEncoder().encode("Default@123"); // Secure default password
            User newUser = new User(email, defaultPassword, true);  // Pass the hashed password
            userRepository.save(newUser);
        } else {
            // Activate the user after OTP verification
            User user = userOptional.get();
            user.setActive(true);
            userRepository.save(user);
        }
    }

    public void logout(String email) {
        // Clear OTP or session data (modify based on your authentication mechanism)
        otpStorage.remove(email); // If using an OTP storage map
    }


}
