package com.aadim.project.service;

import com.aadim.project.dto.request.StudentRequest;
import com.aadim.project.dto.response.StudentResponse;
import com.aadim.project.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest request);
}
