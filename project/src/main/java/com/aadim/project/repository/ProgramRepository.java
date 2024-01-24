package com.aadim.project.repository;

import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
