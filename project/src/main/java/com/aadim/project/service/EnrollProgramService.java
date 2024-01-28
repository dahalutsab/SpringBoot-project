package com.aadim.project.service;

import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.response.EnrollProgramResponse;
import com.aadim.project.dto.response.EnrollStudentDetailResponse;
import com.aadim.project.dto.response.EnrollStudentResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EnrollProgramService {
    EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest) throws MessagingException;

    EnrollStudentResponse getStudentsEnrolledInProgram(Integer programId);

    List<EnrollStudentDetailResponse> getProgramEnrolledByStudent(Integer userId);
    String deleteProgramById(Integer id);

    String deleteStudentById(Integer userId);

//    List<EnrollProgramResponse> getAllEnrollmentsByProgramId(Integer programId);

//    List<EnrollStudentDetailResponse> getAllProgramsOfStudent();

}
