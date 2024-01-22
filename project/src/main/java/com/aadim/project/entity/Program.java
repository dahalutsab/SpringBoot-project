package com.aadim.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDate.from(LocalDateTime.now());
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<EnrollProgram> enrollPrograms = new HashSet<>();


}
