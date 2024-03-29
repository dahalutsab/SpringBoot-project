package com.aadim.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnrollProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;


    //    private String roleName;
    private LocalDate enrollmentDate;
    @PrePersist
    public void prePersist() {
        this.enrollmentDate = LocalDate.from(LocalDateTime.now());
    }

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    public EnrollProgram(Program program, User user) {
        this.program = program;
        this.user = user;
    }

    public Integer getUserId() {
        return this.user.getId();
    }
}
