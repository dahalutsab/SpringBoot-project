package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.request.ProgramRequest;
import com.aadim.project.dto.response.GlobalAPIResponse;
import com.aadim.project.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProgramController extends BaseController {
    private final ProgramService programService;

    @PostMapping("/save/program")
    public ResponseEntity<GlobalAPIResponse> saveProgram(@RequestBody ProgramRequest programRequest){
        return successResponse(programService.saveProgram(programRequest));
    }
}
