package com.aadim.project.dto.response;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Integer id;
    private String name;
    private String description;

    public RoleResponse(Role savedUser) {
        this.id = savedUser.getId();
        this.name = savedUser.getName();
        this.description = savedUser.getDescription();
    }
}
