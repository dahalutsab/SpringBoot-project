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
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stu_id")
    private Integer id;

    @Column(name = "stu_firstName")
    private String firstName;

    @Column(name = "stu_lastName")
    private String lastName;

    @Column(name = "stu_contactNum")
    private String contactNum;

    @Column(name = "stu_email")
    private String email;

    @Column(name = "stu_dob")
    private Date dateOfBirth;

    @Column(name = "stu_age")
    private Integer age;

}
