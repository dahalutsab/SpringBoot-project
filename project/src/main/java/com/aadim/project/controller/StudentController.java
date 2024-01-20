package com.aadim.project.controller;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.dto.request.StudentRequest;
import com.aadim.project.dto.request.StudentUpdateRequest;
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
    public ResponseEntity<GlobalApiResponse> saveStudent(@RequestBody StudentRequest studentRequest) {
        return successResponse(studentService.saveStudent(studentRequest));
    }

    @GetMapping("/fetch")
//    public List<StudentResponse> getAll() {
//        return studentService.getAllStudents();
//    }
    public ResponseEntity<GlobalApiResponse> getAllStudents() {
        return successResponse(studentService.getAllStudents());
    }

    @GetMapping("/fetch/{id}")
    private ResponseEntity<GlobalApiResponse> getStudentByID (@PathVariable Integer id) {
        return successResponse(studentService.getByID(id));
    }

    @PutMapping("update/{id}")
    private ResponseEntity<GlobalApiResponse> updateStudentByID (@RequestBody StudentUpdateRequest studentUpdateRequest) {
        return successResponse(studentService.updateStudent(studentUpdateRequest));
    }
}
