package com.aadim.project.dto.response;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String fullName;
    private String email;
    private String contactNum;
    private String role;

    public UserResponse(User savedUser) {
        this.id = savedUser.getId();
        this.fullName = savedUser.getFullName();
        this.email = savedUser.getEmail();
        this.contactNum = savedUser.getContactNum();
        this.role = savedUser.getRole().getName();
    }
}
