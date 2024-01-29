package com.aadim.project.repository;

import com.aadim.project.dto.response.ProgramResponse;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Page<Program> findAllByIsActive(boolean isActive, Pageable pageable);

//    @Query(
//            nativeQuery = true,
//            value = "update program set is_active = false where id = 152"
//    )
//    void deleteProgramById(Integer id);
}
