package com.candidate.dto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryResponse<T> {
    private long total;
    private List<T> data;
    private String status;
}
