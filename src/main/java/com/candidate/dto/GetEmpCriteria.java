package com.candidate.dto;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GetEmpCriteria {
    private Integer page;
    private Integer numberPerPage;
    private String employeeName;
    private String email;
    private List<String> skill;
    private Map<String, String> sorts; // key = field, value = ASC/DESC
}
