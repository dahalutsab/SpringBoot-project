package com.aadim.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @PrePersist
    public void prePersist() {
        this.enrollmentDate = LocalDate.from(LocalDateTime.now());
    }

    public EnrollProgram(Program program, User user) {
        this.program = program;
        this.user = user;
    }
}
