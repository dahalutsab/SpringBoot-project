package com.aadim.project.dto.request;

import com.aadim.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequest {
    private String topic;
    private String description;
    private Date createdDate;
    private Date eventHappeningDate;
    private Integer userId;
}
