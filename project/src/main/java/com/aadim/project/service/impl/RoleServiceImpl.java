package com.aadim.project.service.impl;

import com.aadim.project.dto.response.RoleResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


//    @Override
//    public List<Role> getAllRoles() {
//
//        return roleRepository.findAll();
//    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> userResponses = new ArrayList<>();
        List<Role> users = roleRepository.findAll();

        for (Role user : users) {
            userResponses.add(new RoleResponse(user));
        }
        return userResponses;
    }

    @Override
    public RoleResponse getRoleOfTeacher() {
        Role teacherRole = roleRepository.findByName("TEACHER");

        if (teacherRole == null) {
            throw new RuntimeException("Teacher role not found");
        }

        return new RoleResponse(teacherRole);
    }

//    get role by id
    @Override
    public RoleResponse getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return new RoleResponse(role);
    }

    @Override
    public RoleResponse getRoleOfStudent() {
        Role studentRole = roleRepository.findByName("STUDENT");

        if (studentRole == null) {
            throw new RuntimeException("Student role not found");
        }

        return new RoleResponse(studentRole);
    }
}
