package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.candidate.model.Employee;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Entering getAllEmployees API");
        List<Employee> employees = // existing logic to fetch employees;
        logger.info("Exiting getAllEmployees API with result: {}", employees);
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Entering getEmployeeById API with id: {}", id);
        Employee employee = // existing logic to fetch employee by id;
        logger.info("Exiting getEmployeeById API with result: {}", employee);
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Entering createEmployee API with input: {}", employee);
        Employee createdEmployee = // existing logic to create employee;
        logger.info("Exiting createEmployee API with result: {}", createdEmployee);
        return createdEmployee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Entering updateEmployee API with id: {} and input: {}", id, employee);
        Employee updatedEmployee = // existing logic to update employee;
        logger.info("Exiting updateEmployee API with result: {}", updatedEmployee);
        return updatedEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Entering deleteEmployee API with id: {}", id);
        // existing logic to delete employee;
        logger.info("Exiting deleteEmployee API");
    }
}