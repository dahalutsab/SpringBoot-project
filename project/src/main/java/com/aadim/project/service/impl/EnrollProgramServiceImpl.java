package com.aadim.project.service.impl;//package com.aadim.project.service.impl;


import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.EnrollProgramResponse;
import com.aadim.project.dto.response.EnrollStudentDetailResponse;
import com.aadim.project.dto.response.EnrollStudentResponse;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.repository.EnrollProgramRepository;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.EnrollProgramService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollProgramServiceImpl implements EnrollProgramService {

    @Autowired
    private MailServiceImpl mailService;

    private final EnrollProgramRepository enrollProgramRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    @Override
    public EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest) throws MessagingException {
        // Retrieve User and Program entities from the database
        User user = userRepository.findById(enrollRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + enrollRequest.getUserId()));

        Program program = programRepository.findById(enrollRequest.getProgramId())
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + enrollRequest.getProgramId()));
        // Check if the user is already enrolled in the program
        if (enrollProgramRepository.existsByUserAndProgram(user, program)) {
            // Handle case where the user is already enrolled
            throw new RuntimeException("User is already enrolled in the program");
        }
        // Create and save EnrollProgram entity
        EnrollProgram enrollProgram = new EnrollProgram(program, user);
         enrollProgramRepository.save(enrollProgram);


        mailService.sendHtmlMail(user.getEmail(), "Student Enrolled In Program", "Hey! New Student Enrolled in your Program. Details: "+ enrollRequest);

         return new EnrollProgramResponse(enrollProgram, user);
    }



    public List<EnrollStudentResponse> getStudentsEnrolledInProgram(Integer programId) {
        List<EnrollProgram> enrollments = enrollProgramRepository.findByProgramId(programId);
        List<Map<String,Object>> userResponseList = enrollProgramRepository.getAllStudentsByProgramId(programId);

        // Convert EnrollProgram entities to EnrollProgramResponse DTOs
        return enrollments.stream()
                .map(enrollment -> new EnrollStudentResponse(enrollment.getId(),
                        enrollment.getProgram().getTitle(), enrollment.getEnrollmentDate(),
                        userResponseList))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollStudentDetailResponse> getProgramEnrolledByStudent(Integer userId){
        List<EnrollStudentDetailResponse> enrollStudentDetailResponses = new ArrayList<>();
        List<EnrollProgram> enrollPrograms = enrollProgramRepository.findByUserId(userId);
        return enrollPrograms.stream()
                .map(enrollment -> new EnrollStudentDetailResponse(enrollment, enrollment.getProgram()))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteProgramById(Integer id){
        enrollProgramRepository.deleteById(id);
        return " Program with id " +id+ " deleted successfully";
    }
}
