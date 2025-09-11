package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        // Implementation for adding employee
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("getAllEmployees method invoked");
        // Implementation for retrieving all employees
        return null;
    }
}
