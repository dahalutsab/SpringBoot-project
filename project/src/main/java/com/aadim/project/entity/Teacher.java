package com.aadim.project.entity;

import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teach_id")
    private Integer id;

    @Column(name = "teach_fullName")
    private String fullName;


    @Column(name = "teach_contactNum")
    private String contactNum;

    @Column(name = "teach_email")
    private String email;

}
