package com.aadim.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String contactNum;

    private String email;

    private Date dateOfBirth;

    private Integer age;
}
