package com.candidate.dto;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String personalEmail;
    private String gender;
    private LocalDate dob;
    private String recruiter;
    private Date date;
    private String source;
    private String name;
    private String experience;
    private String phone;
    private String email;
    private String latestOrg;
    private List<String> skill;
    private String noticePeriod;
    private double currentSalary;
    private double expectedSalary;
    private String offerInHand;
}
