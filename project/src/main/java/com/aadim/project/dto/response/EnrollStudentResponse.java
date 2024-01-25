package com.aadim.project.dto.response;

import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollStudentResponse {
    private Integer id;
    private String program;
    private LocalDate enrollmentDate;
    private List<Map<String,Object>> userResponses;


}
