package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.service.EnrollProgramService;
import com.aadim.project.service.ProgramService;
import com.aadim.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class EnrollProgramController extends BaseController {

    private final ProgramService programService;

    private final EnrollProgramService enrollProgramService;

    private final UserService userService;

    @GetMapping("/fetch")
    public ResponseEntity<GlobalApiResponse> findAll() {
        return successResponse(programService.getAllProgram());
    }





    @PostMapping("/enroll")
    public ResponseEntity<GlobalApiResponse> enrollUserInProgram(@RequestBody EnrollProgramRequest enrollRequest) {
        try {
            enrollProgramService.enrollUserInProgram(enrollRequest);
            return successResponse(enrollRequest);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,  "Error during enrollment: " , e);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAllEnrolled (@RequestBody Integer programID) {
        return successResponse(enrollProgramService.getUsersEnrolledInProgram(programID));
    }
}
