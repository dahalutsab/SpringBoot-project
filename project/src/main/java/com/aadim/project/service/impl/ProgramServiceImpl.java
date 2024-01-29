package com.aadim.project.service.impl;

import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import com.aadim.project.repository.EnrollProgramRepository;
import com.aadim.project.repository.ProgramRepository;
import com.aadim.project.repository.UserRepository;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aadim.project.dto.request.ProgramSaveRequest;
import com.aadim.project.dto.request.ProgramUpdateRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;
    @Autowired
    private EnrollProgramRepository enrollProgramRepository;

    @Override
    public ProgramResponse saveEvent(ProgramSaveRequest request){
        log.info("Saving program: {} ", request);
        Program program = new Program();
        program.setTitle(request.getTitle());
        program.setDescription(request.getDescription());
        program.setVenue(request.getVenue());
        program.setEventType(request.getEventType());
//        program.setCreatedBy(request.getCreatedBy());
//        program.setCreatedDate(request.getCreatedDate());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        program.setUser(user);
        Program savedEvent = programRepository.save(program);

//        SecurityContextHolder.getContext().getAuthentication().getName()
        return new ProgramResponse(program);
    }



//    @Override
//    public List<ProgramResponse> getAllProgram(){
//        log.info("Getting all programs");
//        List<ProgramResponse> programResponses = new ArrayList<>();
//        List<Program> programs = programRepository.findAll();
//        for(Program program: programs){
//            programResponses.add(new ProgramResponse(program));
//        }
//        return programResponses;
//    }

    @Override
    public List<ProgramResponse> getAllProgram(int page, int pageSize) {
        log.info("Getting all programs with pagination");
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Program> programPage = programRepository.findAllByIsActive(true, pageable);

        List<ProgramResponse> programResponses = programPage.getContent().stream()
                .map(ProgramResponse::new)
                .collect(Collectors.toList());

        return programResponses;
    }

    @Override
    public String deleteProgram(Integer id){
        log.info("Deleting program with id: {} ", id);
        if (!programRepository.existsById(id)) {
            log.warn("Program not found with id: {}", id);
            throw new UsernameNotFoundException("Program not found");
        }
        Program program = programRepository.findById(id).orElse(null);
        List<EnrollProgram> enrollProgram = enrollProgramRepository.findByProgramId(id);
        if(!program.getIsActive()){
            log.warn("Cannot delete user as it is inactive: {}", program);
            throw new RuntimeException("User not found");
        }
//            enrollProgramRepository.deleteEnrollmentsForProgram(id);
//            programRepository.deleteProgramById(id);
        for (EnrollProgram enroll: enrollProgram){
            enroll.setIsActive(false);
        }
            program.setIsActive(false);
            programRepository.save(program);
            return " Program with id " +id+ " deleted successfully";
    }

    @Override
    public ProgramResponse updateProgram(ProgramUpdateRequest request){
        log.info("Updating program: {} ", request);
        Program program = programRepository.getReferenceById(request.getId());
        program.setTitle(request.getTitle());
        program.setDescription(request.getDescription());
        program.setVenue(request.getVenue());
        program.setEventType(request.getEventType());
        program.setLastModifiedDate(LocalDate.now());
//        program.setCreatedBy(request.getCreatedBy());
        Program savedEvent = programRepository.save(program);
        return new ProgramResponse(savedEvent);

    }

    @Override
    public boolean isStudentEnrolled(Integer programId, Long userId) {
        return false;
    }

    @Override
    public ProgramResponse getById(Integer id){
        try {
            log.info("Getting program by id: {} ", id);
            Program program = programRepository.getReferenceById(id);
            return new ProgramResponse(program);
        } catch (Exception e) {
            log.error("Error getting program by id: " + id, e);
            throw e;
        }
    }




}
