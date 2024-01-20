package com.aadim.project.service.impl;

import com.aadim.project.entity.Role;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
