package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController extends BaseController {

    private final RoleService roleService;
    @GetMapping("/getAllRoles")
    public ResponseEntity<GlobalApiResponse> getAll () {
        return successResponse(roleService.getAllRoles());
    }

    @GetMapping("/getRoleOfTeacher")
    public ResponseEntity<GlobalApiResponse> getRoleOfTeacher () {
        return successResponse(roleService.getRoleOfTeacher());
    }

    @GetMapping("/getRoleOfStudent")
    public ResponseEntity<GlobalApiResponse> getRoleOfStudent () {
        return successResponse(roleService.getRoleOfStudent());
    }

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<GlobalApiResponse> getRoleById (@PathVariable Integer id) {
        log.warn("id: {}", id);
            return successResponse(roleService.getRoleById(id));
    }
}
