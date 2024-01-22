package com.aadim.project.service.impl;//package com.aadim.project.service.impl;
//
//import com.aadim.project.entity.EnrollProgram;
//import com.aadim.project.repository.EnrollProgramRepository;
//import com.aadim.project.service.EnrollProgramService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EnrollProgramServiceImpl implements EnrollProgramService {
//    @Autowired
//    private EnrollProgramRepository enrollProgramRepository;
//
//    @Override
//    public void saveEnrollment(EnrollProgram enrollment) {
//
//        enrollProgramRepository.save(enrollment);
//    }
//}


import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.request.EnrollProgramResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.repository.EnrollProgramRepository;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.EnrollProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollProgramServiceImpl implements EnrollProgramService {

    private final EnrollProgramRepository enrollProgramRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest) {
        // Retrieve User and Program entities from the database
        User user = userRepository.findById(enrollRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + enrollRequest.getUserId()));

        Program program = programRepository.findById(enrollRequest.getProgramId())
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + enrollRequest.getProgramId()));

        // Create and save EnrollProgram entity
        EnrollProgram enrollProgram = new EnrollProgram(program, user);
         enrollProgramRepository.save(enrollProgram);
         return new EnrollProgramResponse(enrollProgram);
    }


}
