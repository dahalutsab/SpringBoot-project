package com.aadim.project.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProgramUpdateRequest {
    private Integer id;
    private String title;
    private String description;
    private String venue;
    private String eventType;
    private LocalDate createdDate;
    private Integer userId;
}
