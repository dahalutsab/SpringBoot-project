package com.aadim.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

//    private String roleName;
    private LocalDate enrollmentDate;

    public EnrollProgram(Program program, User user, LocalDate enrollmentDate) {
        this.program = program;
        this.user = user;
        this.enrollmentDate = enrollmentDate;
    }
}
