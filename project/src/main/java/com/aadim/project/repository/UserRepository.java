package com.aadim.project.repository;

import com.aadim.project.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where role_id=:teacherId"
    )
    List<User> getUserByRole_id(Integer teacherId);

    List<User> findAllByIsActive(Boolean isActive);


    @Query(
            nativeQuery = true,
            value = "select email from users where email=:email"
    )
    String getEmail(String email);


    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Integer getUserIdByEmail(String email);
    boolean existsByEmail(String email);

    @Query(
            nativeQuery = true,
            value = "select * from users where email=:email"
    )
    User getUserByEmail(String email);

    Page<User> findAllByIsActive(boolean isActive, Pageable pageable);
}
