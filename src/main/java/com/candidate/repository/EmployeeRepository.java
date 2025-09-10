package com.candidate.repository;

import com.candidate.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    boolean existsByEmailOrPhone(String personalEmail, String phone);
}


