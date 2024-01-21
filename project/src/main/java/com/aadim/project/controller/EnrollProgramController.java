package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
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
    @Autowired
    private ProgramService programService;
    @Autowired
    private EnrollProgramService enrollProgramService;
    @Autowired
    private UserService userService;

    @GetMapping("/fetch")
    public ResponseEntity<GlobalApiResponse> findAll(){
        return successResponse(programService.getAllProgram());
    }

    @PostMapping("/enroll")
    public ResponseEntity<GlobalApiResponse> saveStudentEvent(@PathVariable Integer programId,
                                                              @RequestParam String roleName ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentUsername = authentication.getName();
        Program program = programService.getById(programId);
        User student = userService.getUserByUsername(studentUsername);
        // Check if the student is already enrolled in the program
        if (programService.isStudentEnrolled(programId, userId)) {
            return ResponseEntity.badRequest().body(new GlobalApiResponse("Student is already enrolled in the program."));
        }

        // Create an enrollment and set details
        EnrollProgram enrollment = new EnrollProgram();
        enrollment.setProgram(program);
        enrollment.setUser(user);
        enrollment.setRoleName(roleName);
        enrollment.setEnrollmentDate(LocalDate.now());

        // Save the enrollment
        enrollProgramService.saveEnrollment(enrollment);

        return ResponseEntity.ok().body(new GlobalApiResponse("Student successfully enrolled in the program."));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalApiResponse("Failed to enroll student in the program."));
    }

//        setDetails Here


//        save to entity
    }







}
