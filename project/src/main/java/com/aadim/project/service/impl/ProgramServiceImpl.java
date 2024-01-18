package com.aadim.project.service.impl;

import com.aadim.project.dto.request.ProgramRequest;
import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.Program;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public ProgramResponse saveProgram(ProgramRequest request){
        Program program = new Program();
        program.setTopic(request.getTopic());
        program.setDescription(request.getDescription());
        program.setCreatedDate(request.getCreatedDate());
        program.setEventHappeningDate(request.getEventHappeningDate());

        Program savedProgram = programRepository.save(program);
        return new ProgramResponse(savedProgram);
    }
}
