package com.aadim.project.service.impl;//package com.aadim.project.service.impl;


import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.EnrollProgramDetailResponse;
import com.aadim.project.dto.response.EnrollProgramResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.repository.EnrollProgramRepository;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.EnrollProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollProgramServiceImpl implements EnrollProgramService {

    private final EnrollProgramRepository enrollProgramRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    @Override
    public EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest) {
        // Retrieve User and Program entities from the database
        User user = userRepository.findById(enrollRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + enrollRequest.getUserId()));

        Program program = programRepository.findById(enrollRequest.getProgramId())
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + enrollRequest.getProgramId()));
//        LocalDate enrollmentDate = LocalDate.now();
        // Create and save EnrollProgram entity
        EnrollProgram enrollProgram = new EnrollProgram(program, user);
         enrollProgramRepository.save(enrollProgram);
         return new EnrollProgramResponse(enrollProgram, user);
    }



    public List<EnrollProgramResponse> getStudentsEnrolledInProgram(Integer programId) {
        List<EnrollProgram> enrollments = enrollProgramRepository.findByProgramId(programId);

        // Convert EnrollProgram entities to EnrollProgramResponse DTOs
        return enrollments.stream()
                .map(enrollment -> new EnrollProgramResponse(enrollment, enrollment.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollProgramDetailResponse> getAllStudentsOfProgram(Integer userId){
        List<EnrollProgramDetailResponse> enrollProgramDetailResponses = new ArrayList<>();
        List<EnrollProgram> enrollPrograms = enrollProgramRepository.findAll();
        for(EnrollProgram enrollment: enrollPrograms){
            enrollProgramDetailResponses.add(new EnrollProgramDetailResponse(enrollment));
        }
        return enrollProgramDetailResponses;
    }

    @Override
    public String deleteProgramById(Integer id){
        enrollProgramRepository.deleteById(id);
        return " Program with id " +id+ " deleted successfully";
    }





}
