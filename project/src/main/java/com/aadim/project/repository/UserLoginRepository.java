package com.aadim.project.repository;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from user_login where user_name=:username"
    )
    UserLogin getUserByUserName(String username);

    boolean existsByUsername(String username);


    @Query("SELECT u.role FROM User u WHERE u.userLogin.username = :username")
    Role getUserRoleByUsername(@Param("username") String username);


}
