package com.aadim.project.dto.response;

import com.aadim.project.entity.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String contactNum;

    private String email;

    private Date dateOfBirth;

    private Integer age;



    public StudentResponse(Student savedStudent) {
        this.id = savedStudent.getId();
        this.firstName = savedStudent.getFirstName();
        this.lastName = savedStudent.getLastName();
        this.email = savedStudent.getEmail();
        this.dateOfBirth = savedStudent.getDateOfBirth();
        this.age = savedStudent.getAge();
    }
}
