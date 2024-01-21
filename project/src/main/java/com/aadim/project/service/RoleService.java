package com.aadim.project.service;

import com.aadim.project.dto.response.RoleResponse;
import com.aadim.project.entity.Role;

import java.util.List;

public interface RoleService {


    List<RoleResponse> getAllRoles();
}
