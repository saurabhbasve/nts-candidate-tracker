package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.candidate.model.Employee;
import com.candidate.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        logger.info("Starting the addEmployee method");
        Employee savedEmployee = employeeService.addEmployee(employee);
        logger.info("Finished the addEmployee method");
        return ResponseEntity.ok(savedEmployee);
    }
}