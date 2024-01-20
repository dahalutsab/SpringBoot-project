package com.aadim.project.dto.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String fullName;
    private String email;
    private String contactNum;
    private String username;
    private String password;
}
