package com.aadim.project.dto.request;

import com.aadim.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String fullName;
    private String email;
    private String contactNum;
    private Integer roleId;
}