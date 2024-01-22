package com.aadim.project.controller;


import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.request.ProgramSaveRequest;
import com.aadim.project.dto.request.ProgramUpdateRequest;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class ProgramController extends BaseController {
    private final ProgramService programService;
    @PostMapping("/save")
    public ResponseEntity<GlobalApiResponse> saveEvent(@RequestBody ProgramSaveRequest request){
        return successResponse(programService.saveEvent(request));
    }

    @GetMapping("fetch")
    public ResponseEntity<GlobalApiResponse> findAll(){
        return successResponse(programService.getAllProgram());
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
        return successResponse(programService.updateProgram(request));
    }

//    @PostMapping("/event/enroll")
//    public ResponseEntity<GlobalApiResponse> saveEnroll(@RequestBody ProgramEventRequest request){
//        return successResponse(programService.saveEvent(request));
//    }


}
