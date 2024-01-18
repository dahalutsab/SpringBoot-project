package com.aadim.project.service.impl;

import com.aadim.project.dto.request.StudentRequest;
import com.aadim.project.dto.response.StudentResponse;
import com.aadim.project.entity.Student;
import com.aadim.project.repository.StudentRepository;
import com.aadim.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setContactNum(request.getContactNum());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setAge(request.getAge());

        Student savedStudent = studentRepository.save(student);
        return new StudentResponse(savedStudent);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        List<Student> students = studentRepository.findAll();

        for (Student student: students ) {
            studentResponses.add(new StudentResponse(student));
        }
        return studentResponses;
    }
}
