package com.aadim.project.service;

import com.aadim.project.dto.request.EnrollProgramRequest;
import com.aadim.project.dto.request.EnrollProgramResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.User;

import java.util.List;

public interface EnrollProgramService {
    EnrollProgramResponse enrollUserInProgram(EnrollProgramRequest enrollRequest);

    List<User> getUsersEnrolledInProgram(Integer programId);
}
