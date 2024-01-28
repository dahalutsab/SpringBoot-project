package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.EnrollProgramDetailResponse;
import com.aadim.project.dto.response.EnrollProgramResponse;
import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.service.EnrollProgramService;
import com.aadim.project.service.ProgramService;
import com.aadim.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EnrollProgramController extends BaseController {

    private final ProgramService programService;

    private final EnrollProgramService enrollProgramService;

    private final UserService userService;

//    @GetMapping("/fetch")
//    public ResponseEntity<GlobalApiResponse> findAll() {
//        return successResponse(programService.getAllProgram());
//    }





    @PostMapping("/student/enroll")
    public ResponseEntity<GlobalApiResponse> enrollUserInProgram(@RequestBody EnrollProgramRequest enrollRequest) {
        try {
            enrollRequest.setEnrollmentDate(LocalDate.now());
            enrollProgramService.enrollUserInProgram(enrollRequest);
            return successResponse(enrollRequest, "Student Enrolled in the program Successful");
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,  "Error during enrollment: " , e);
        }
    }
    //send Program id
    @GetMapping("/student/getAll/{programId}")
    public ResponseEntity<GlobalApiResponse> getAllData (@PathVariable Integer programId) {
        return successResponse(enrollProgramService.getStudentsEnrolledInProgram(programId));
    }

    @GetMapping("/student/getAllDetails/{userId}")
    public ResponseEntity<GlobalApiResponse> getAllStudents (@PathVariable Integer userId) {
        return successResponse(enrollProgramService.getProgramEnrolledByStudent(userId));
    }
//    @GetMapping("/student/getStudent/{id}")
//    public ResponseEntity<GlobalApiResponse> getById(@PathVariable Integer id){
//        return successResponse(enrollProgramService.getAllStudentsOfProgram(id));
//    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<GlobalApiResponse> deleteProgramById(@PathVariable Integer id){
        return successResponse(enrollProgramService.deleteProgramById(id));
    }

//    @GetMapping("/student/getAllStudentsInProgram/{programId}")
//    public ResponseEntity<GlobalApiResponse> getAllStudents(@PathVariable Integer programId){
//        return successResponse(enrollProgramService.getAllEnrollmentsByProgramId(programId));
//    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<GlobalApiResponse> deleteStudentById(@PathVariable Integer id){
        String message = enrollProgramService.deleteStudentById(id);
        return successResponse(message);
    }

//    @Transactional
//    public void deleteEnrollmentsForProgram(Integer programId) {
//        enrollProgramRepository.deleteEnrollmentsForProgram(programId);
//    }
}
