package com.aadim.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private String fullName;

    private String contactNum;

    private String email;
}
