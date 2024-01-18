package com.aadim.project.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {

    private Integer id;

    private String fullName;

//    private String firstName;

//    private String lastName;

    private String contactNum;

    private String email;

//    private Date dateOfBirth;
//
//    private Integer age;
}
