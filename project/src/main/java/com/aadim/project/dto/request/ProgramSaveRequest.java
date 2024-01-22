package com.aadim.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramSaveRequest {
    private String title;
    private String description;
    private String venue;
    private String eventType;
    private Integer userId;
}
