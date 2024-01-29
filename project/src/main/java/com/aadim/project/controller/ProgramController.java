package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.request.ProgramSaveRequest;
import com.aadim.project.dto.request.ProgramUpdateRequest;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.Program;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class ProgramController extends BaseController {
    private final ProgramService programService;
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PostMapping("/save")
    public ResponseEntity<GlobalApiResponse> saveEvent(@RequestBody ProgramSaveRequest request){
        return successResponse(programService.saveEvent(request),"Program created successfully.");
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','STUDENT')")
    @GetMapping("/fetch")
    public ResponseEntity<GlobalApiResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws Exception
    {
        return successResponse(programService.getAllProgram(page, size));
    }

    @GetMapping("fetch/{id}")
    public ResponseEntity<GlobalApiResponse> getById(@PathVariable Integer id){
        return successResponse(programService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteProgram(@PathVariable Integer id){
        return successResponse(programService.deleteProgram(id));
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateProgram(@RequestBody ProgramUpdateRequest request){
        return successResponse(programService.updateProgram(request), "Program updated successfully.");
    }

//    @PostMapping("/event/enroll")
//    public ResponseEntity<GlobalApiResponse> saveEnroll(@RequestBody ProgramEventRequest request){
//        return successResponse(programService.saveEvent(request));
//    }

//    @GetMapping("/engaged-student/{id}")
//    public ResponseEntity<List<Program>> getProgramsForEngagedStudent(@PathVariable Integer id) {
//        List<ProgramResponse> programs = programService.getProgramsForEngagedStudent(id);
//        return new ResponseEntity<>(programs, HttpStatus.OK);
//    }




}
