package com.candidate.controller;

import com.candidate.dto.GetEmpCriteria;
import com.candidate.dto.QueryResponse;
import com.candidate.exception.DuplicateEmployeeException;
import com.candidate.model.Employee;
import com.candidate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(
        origins = {
                "*",
                "http://localhost:4200",
                "http://localhost:9876"
        })
@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {
        Map<String, Object> response = new HashMap<>();

        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            response.put("message", "Employee added successfully");
            response.put("employeeId", savedEmployee.getId());
            return ResponseEntity.ok(response);
        } catch (DuplicateEmployeeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<QueryResponse<Map<String, Object>>> getAllEmployees(@RequestBody GetEmpCriteria criteria) {
        return ResponseEntity.ok(employeeService.getAllEmployees(criteria));
    }
}
