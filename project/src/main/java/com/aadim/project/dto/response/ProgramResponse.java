package com.aadim.project.dto.response;

import com.aadim.project.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramResponse {
    private Integer id;
    private String topic;
    private String description;
    private Date createdDate;
    private Date eventHappeningDate;

    public ProgramResponse(Program program){
       this.id = program.getId();
       this.topic = program.getTopic();
       this.description = program.getDescription();
       this.createdDate = program.getCreatedDate();
       this.eventHappeningDate = program.getEventHappeningDate();
    }
}
