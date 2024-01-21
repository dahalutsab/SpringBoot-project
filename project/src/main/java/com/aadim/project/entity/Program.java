package com.aadim.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String venue;
    private String eventType;
    private LocalDate createdDate;
    private String createdBy;

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<EnrollProgram> enrollPrograms = new HashSet<>();


}
