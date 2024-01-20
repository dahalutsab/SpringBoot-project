package com.aadim.project.dto.request;

import com.aadim.project.entity.Role;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String fullName;
    private String email;
    private String contactNum;
    private String username;
    private String password;
    private Integer roleId;
}
