package com.aadim.project.dto.response;

import com.aadim.project.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramResponse {
    private Integer id;
    private String title;
    private String description;
    private String venue;
    private String eventType;
    private LocalDate createdDate;
    private String userId;

    public ProgramResponse(Program program) {
        this.id = program.getId();
        this.title = program.getTitle();
        this.description = program.getDescription();
        this.venue = program.getVenue();
        this.eventType = program.getEventType();
        this.createdDate = program.getCreatedDate();
        this.userId = program.getUser().getFullName();
    }
}
