package com.aadim.project.dto.request;


import com.aadim.project.entity.EnrollProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollProgramResponse {
    private Integer id;
    private Integer userId;
    private Integer programId;
    private LocalDate enrollmentDate;

    public EnrollProgramResponse(EnrollProgram enrollProgram) {
        this.id = enrollProgram.getId();
        this.userId = enrollProgram.getUser().getId();
        this.programId = enrollProgram.getProgram().getId();
        this.enrollmentDate = enrollProgram.getEnrollmentDate();
    }
}
