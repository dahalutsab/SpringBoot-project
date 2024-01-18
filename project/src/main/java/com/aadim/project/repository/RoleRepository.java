package com.aadim.project.repository;

import com.aadim.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from role where name=:name"
    )
    Role findByName(String name);
}
