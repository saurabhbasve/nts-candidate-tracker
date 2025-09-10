package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Start: getAllEmployees");
        List<Employee> employees = employeeService.findAll();
        logger.info("End: getAllEmployees");
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Start: getEmployeeById with id: {}", id);
        Employee employee = employeeService.findById(id);
        logger.info("End: getEmployeeById with id: {}", id);
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Start: createEmployee");
        Employee createdEmployee = employeeService.save(employee);
        logger.info("End: createEmployee");
        return createdEmployee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Start: updateEmployee with id: {}", id);
        Employee updatedEmployee = employeeService.update(id, employee);
        logger.info("End: updateEmployee with id: {}", id);
        return updatedEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Start: deleteEmployee with id: {}", id);
        employeeService.delete(id);
        logger.info("End: deleteEmployee with id: {}", id);
    }
}