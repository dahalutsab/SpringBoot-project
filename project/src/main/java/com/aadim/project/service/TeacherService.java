package com.aadim.project.service;


import com.aadim.project.dto.request.TeacherRequest;
import com.aadim.project.dto.response.TeacherResponse;
import org.springframework.http.ResponseEntity;

public interface TeacherService {
    TeacherResponse saveTeacher (TeacherRequest teacherRequest);
}
