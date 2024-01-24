package com.aadim.project.service;

import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.dto.request.ProgramSaveRequest;
import com.aadim.project.dto.request.ProgramUpdateRequest;

import java.util.List;

public interface ProgramService {
    ProgramResponse saveEvent(ProgramSaveRequest request);

    List<ProgramResponse> getAllProgram();

    ProgramResponse getById(Integer id);
    String deleteProgram(Integer id);

//    ProgramResponse saveEvent(ProgramEventRequest request);

    ProgramResponse updateProgram(ProgramUpdateRequest request);

    boolean isStudentEnrolled(Integer programId, Long userId);

}
