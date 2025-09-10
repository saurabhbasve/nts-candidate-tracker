package com.candidate.service;
import com.candidate.dto.GetEmpCriteria;
import com.candidate.dto.QueryResponse;
import com.candidate.exception.DuplicateEmployeeException;
import com.candidate.model.Employee;
import com.candidate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Employee addEmployee(Employee employee) {
        boolean exists = employeeRepository.existsByEmailOrPhone(employee.getPersonalEmail(), employee.getPhone());
        if (exists) {
            throw new DuplicateEmployeeException("Employee with the same email or phone number already exists.");
        }
        return employeeRepository.save(employee);
    }

    public QueryResponse<Map<String, Object>> getAllEmployees(GetEmpCriteria criteria) {
        try {
            Query query = new Query();

            if (criteria.getEmployeeName() != null) {
                query.addCriteria(Criteria.where("employeeName").regex(criteria.getEmployeeName(), "i"));
            }
            if (criteria.getEmail() != null) {
                query.addCriteria(Criteria.where("email").is(criteria.getEmail()));
            }
            if (criteria.getSkill() != null && !criteria.getSkill().isEmpty()) {
                query.addCriteria(Criteria.where("skill").in(criteria.getSkill()));
            }

            long total = mongoTemplate.count(query, Employee.class);

            // Sorting
            Sort sort = Sort.by(Sort.Direction.ASC, "employeeName");
            if (criteria.getSorts() != null && !criteria.getSorts().isEmpty()) {
                List<Sort.Order> orders = criteria.getSorts().entrySet().stream()
                        .map(entry -> new Sort.Order(Sort.Direction.fromString(entry.getValue()), entry.getKey()))
                        .collect(Collectors.toList());
                sort = Sort.by(orders);
            }

            // Pagination
            PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getNumberPerPage(), sort);
            query.with(pageable);

            // Fetch paginated data
            List<Employee> employees = mongoTemplate.find(query, Employee.class);

            List<Map<String, Object>> mappedEmployees = employees.stream()
                    .map(this::convertToMap)
                    .collect(Collectors.toList());

            return new QueryResponse<>(total, mappedEmployees, "200");

        } catch (Exception e) {
            return new QueryResponse<>(0, Collections.emptyList(), "500");
        }
    }

    private Map<String, Object> convertToMap(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("employeeId", employee.getId());
        map.put("employeeName", employee.getEmployeeName());
        map.put("personalEmail", employee.getPersonalEmail());
        map.put("gender", employee.getGender());
        map.put("dob", employee.getDob());
        map.put("recruiter", employee.getRecruiter());
        map.put("date", employee.getDate());
        map.put("source", employee.getSource());
        map.put("name", employee.getName());
        map.put("experience", employee.getExperience());
        map.put("phone", employee.getPhone());
        map.put("email", employee.getEmail());
        map.put("latestOrg", employee.getLatestOrg());
        map.put("skill", employee.getSkill());
        map.put("noticePeriod", employee.getNoticePeriod());
        map.put("currentSalary", employee.getCurrentSalary());
        map.put("expectedSalary", employee.getExpectedSalary());
        map.put("offerInHand", employee.getOfferInHand());
        return map;
    }
}