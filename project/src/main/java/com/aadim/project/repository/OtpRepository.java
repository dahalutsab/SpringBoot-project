package com.aadim.project.repository;

import com.aadim.project.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Query(
            nativeQuery = true,
            value = "select otp from tbl_otp where email=:email"
    )
    Integer existsByEmail(String email);

}
