package com.aadim.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class EnrollProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String roleName;
    private LocalDate enrollmentDate;
}
