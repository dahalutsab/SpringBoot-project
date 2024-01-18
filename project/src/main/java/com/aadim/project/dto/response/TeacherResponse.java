package com.aadim.project.dto.response;

import com.aadim.project.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Integer id;

    private String fullName;

    private String contactNum;

    private String email;




    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.fullName = teacher.getFullName();
        this.contactNum = teacher.getContactNum();
        this.email = teacher.getEmail();
    }
}
