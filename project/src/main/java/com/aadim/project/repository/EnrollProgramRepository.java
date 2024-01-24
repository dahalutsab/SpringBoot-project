package com.aadim.project.repository;

import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollProgramRepository extends JpaRepository<EnrollProgram, Integer> {

    @Query("SELECT u.role FROM User u WHERE u.userLogin.username = :username")
    EnrollProgram getAll(@Param("username") String username);

//    List<EnrollProgram> findByUserId(Integer userId);
    List<EnrollProgram> findByProgramId(Integer programId);

    List<EnrollProgram> findByProgram(Program program);
}
