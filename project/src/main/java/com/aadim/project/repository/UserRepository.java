package com.aadim.project.repository;

import com.aadim.project.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserLogin, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where user_name=:username"
    )
    UserLogin getUserByUserName(String username);
}
