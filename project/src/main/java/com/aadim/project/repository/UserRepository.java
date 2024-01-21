package com.aadim.project.repository;

import com.aadim.project.dto.request.UserRequest;
import com.aadim.project.dto.response.UserResponse;
import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where role_id=:teacherId"
    )
    List<User> getUserByRole_id(Integer teacherId);




}
