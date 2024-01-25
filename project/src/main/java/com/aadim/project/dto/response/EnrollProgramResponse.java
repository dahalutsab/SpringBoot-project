package com.aadim.project.dto.response;


import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollProgramResponse {
    private Integer id;
//    private User user;
    private String program;
    private LocalDate enrollmentDate;

    private UserResponse userResponse;



    public EnrollProgramResponse(EnrollProgram enrollProgram, User user) {
        this.id = enrollProgram.getId();
        this.userResponse = new UserResponse(user);
        this.program = enrollProgram.getProgram().getTitle();
        this.enrollmentDate = enrollProgram.getEnrollmentDate();
    }

}
