package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.candidate.service.EmployeeService;
import com.candidate.model.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    // Existing code...

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        logger.info("Received request to create employee: {}", employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        logger.info("Employee created successfully: {}", createdEmployee);
        return ResponseEntity.ok(createdEmployee);
    }
}
