package com.aadim.project.repository;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(Role teacherRole);
}
