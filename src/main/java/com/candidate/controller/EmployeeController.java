package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Entering getAllEmployees API");
        // Business logic to get all employees
        List<Employee> employees = employeeService.getAllEmployees();
        logger.info("Exiting getAllEmployees API");
        return employees;
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Entering getEmployeeById API with id: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        logger.info("Exiting getEmployeeById API");
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Entering createEmployee API");
        Employee createdEmployee = employeeService.createEmployee(employee);
        logger.info("Exiting createEmployee API");
        return createdEmployee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Entering updateEmployee API with id: {}", id);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        logger.info("Exiting updateEmployee API");
        return updatedEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Entering deleteEmployee API with id: {}", id);
        employeeService.deleteEmployee(id);
        logger.info("Exiting deleteEmployee API");
    }
}