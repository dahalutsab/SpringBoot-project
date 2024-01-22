package com.aadim.project.service.impl;

import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.Program;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aadim.project.dto.request.ProgramSaveRequest;
import com.aadim.project.dto.request.ProgramUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public ProgramResponse saveEvent(ProgramSaveRequest request){
        Program program = new Program();
        program.setTitle(request.getTitle());
        program.setDescription(request.getDescription());
        program.setVenue(request.getVenue());
        program.setEventType(request.getEventType());
        program.setCreatedBy(request.getCreatedBy());
        program.setCreatedDate(request.getCreatedDate());

        Program savedEvent = programRepository.save(program);

//        SecurityContextHolder.getContext().getAuthentication().getName()
        return new ProgramResponse(program);
    }

    @Override
    public List<ProgramResponse> getAllProgram(){
        List<ProgramResponse> programResponses = new ArrayList<>();
        List<Program> programs = programRepository.findAll();
        for(Program program: programs){
            programResponses.add(new ProgramResponse(program));
        }
        return programResponses;
    }

    @Override
    public String deleteProgram(Integer id){
        programRepository.deleteById(id);
        return " Program with id " +id+ " deleted successfully";
    }

//    @Override
//    public ProgramResponse saveEvent(ProgramEventRequest request){
//        Program program = new Program();
//        program.setTitle(request.getTitle());
//        program.setDescription(request.getDescription());
//        program.setVenue(request.getVenue());
//        program.setEventType(request.getEventType());
//        program.setCreatedBy(request.getCreatedBy());
//        program.setCreatedDate(request.getCreatedDate());
//        program.setEnrollDate(request.getEnrollDate());
//        program.setEnrollDate(request.getEnrollDate());
//
//        Program savedEvent = programRepository.save(program);
//        return new ProgramResponse(program);
//    }

    @Override
    public ProgramResponse updateProgram(ProgramUpdateRequest request){
        Program program = programRepository.getReferenceById(request.getId());
        program.setTitle(request.getTitle());
        program.setDescription(request.getDescription());
        program.setVenue(request.getVenue());
        program.setEventType(request.getEventType());
        program.setCreatedDate(request.getCreatedDate());
        program.setCreatedBy(request.getCreatedBy());
        Program savedEvent = programRepository.save(program);
        return new ProgramResponse(savedEvent);

    }

    @Override
    public boolean isStudentEnrolled(Integer programId, Long userId) {
        return false;
    }

    @Override
    public ProgramResponse getById(Integer id){
        Program program = programRepository.getReferenceById(id);
        return new ProgramResponse(program);
    }
}
