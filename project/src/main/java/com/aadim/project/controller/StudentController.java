package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.request.StudentRequest;
import com.aadim.project.dto.response.GlobalAPIResponse;
import com.aadim.project.dto.response.StudentResponse;
import com.aadim.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController extends BaseController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<GlobalAPIResponse> saveStudent(@RequestBody StudentRequest studentRequest) {
        return successResponse(studentService.saveStudent(studentRequest));
    }

    @GetMapping("/fetch")
    public List<StudentResponse> getAll() {
        return studentService.getAllStudents();
    }
}
