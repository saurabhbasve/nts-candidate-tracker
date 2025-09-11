package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees...");
        List<Employee> employees = employeeService.findAll();
        logger.info("Fetched {} employees.", employees.size());
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee with id: {}...", id);
        Employee employee = employeeService.findById(id);
        logger.info("Fetched employee: {}", employee);
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Creating employee: {}...", employee);
        Employee createdEmployee = employeeService.save(employee);
        logger.info("Created employee: {}", createdEmployee);
        return createdEmployee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Updating employee with id: {}...", id);
        Employee updatedEmployee = employeeService.update(id, employee);
        logger.info("Updated employee: {}", updatedEmployee);
        return updatedEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with id: {}...", id);
        employeeService.delete(id);
        logger.info("Deleted employee with id: {}", id);
    }
}