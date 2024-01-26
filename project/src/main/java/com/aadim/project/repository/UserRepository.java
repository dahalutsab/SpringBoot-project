package com.aadim.project.repository;

import com.aadim.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where role_id=:teacherId"
    )
    List<User> getUserByRole_id(Integer teacherId);


    @Query(
            nativeQuery = true,
            value = "select email from users where email=:email" // failed to get username
    )
    String getEmail(String email);

    String getEmailById(Integer id);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Integer getUserIdByEmail(String email);

}
