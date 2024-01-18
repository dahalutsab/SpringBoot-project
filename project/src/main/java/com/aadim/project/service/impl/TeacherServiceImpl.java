package com.aadim.project.service.impl;

import com.aadim.project.dto.request.TeacherRequest;
import com.aadim.project.dto.response.TeacherResponse;
import com.aadim.project.entity.Teacher;
import com.aadim.project.repository.TeacherRepository;
import com.aadim.project.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    @Override
    public TeacherResponse saveTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setFullName(teacher.getFullName());
        teacher.setEmail(teacher.getEmail());
        teacher.setContactNum(teacher.getContactNum());

        Teacher saveTeacher = teacherRepository.save(teacher);
        return new TeacherResponse(saveTeacher);
    }
}
