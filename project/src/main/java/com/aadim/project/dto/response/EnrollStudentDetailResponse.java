package com.aadim.project.dto.response;

import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollStudentDetailResponse {
    private Integer id;
    private String userId;
    private ProgramResponse programResponse;
    private LocalDate enrollmentDate;

    public EnrollStudentDetailResponse(EnrollProgram enrollment, Program program) {
        this.id = enrollment.getId();
        this.userId = enrollment.getUser().getFullName();
        this.programResponse = new ProgramResponse(enrollment.getProgram());
        this.enrollmentDate = enrollment.getEnrollmentDate();
    }
}
