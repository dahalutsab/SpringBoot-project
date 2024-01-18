package com.aadim.project.service;

import com.aadim.project.dto.request.ProgramRequest;
import com.aadim.project.dto.response.ProgramResponse;

public interface ProgramService {
    ProgramResponse saveProgram(ProgramRequest request);
}
