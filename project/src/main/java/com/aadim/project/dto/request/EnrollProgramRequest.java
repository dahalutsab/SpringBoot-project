package com.aadim.project.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollProgramRequest {
    private Integer userId;
    private Integer programId;
    private LocalDate enrollmentDate;
}
