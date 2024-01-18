package com.aadim.project.repository;

import com.aadim.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where user_name=:username"
    )
    User getUserByUserName(String username);
}
