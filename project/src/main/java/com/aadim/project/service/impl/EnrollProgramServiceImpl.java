package com.aadim.project.service.impl;

import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.repository.EnrollProgramRepository;
import com.aadim.project.service.EnrollProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollProgramServiceImpl implements EnrollProgramService {
    @Autowired
    private EnrollProgramRepository enrollProgramRepository;

    @Override
    public void saveEnrollment(EnrollProgram enrollment) {
        enrollProgramRepository.save(enrollment);
    }
}
