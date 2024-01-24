package com.aadim.project.service;

import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.EnrollProgramDetailResponse;
import com.aadim.project.dto.response.EnrollProgramResponse;
import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.User;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EnrollProgramService {
    EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest) throws MessagingException;

    List<EnrollProgramResponse> getStudentsEnrolledInProgram(Integer programId);

    List<EnrollProgramDetailResponse> getAllStudentsOfProgram(Integer userId);
    String deleteProgramById(Integer id);

//    List<EnrollProgramDetailResponse> getAllProgramsOfStudent();

}
