package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // Existing methods...

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        logger.info("Creating a new employee: {}", employee);

        // Logic to save employee would go here

        logger.info("Employee created successfully.");
        return ResponseEntity.ok("Employee created successfully");
    }
}