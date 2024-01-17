package com.aadim.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalAPIResponse {
    private String httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private Object data;
}
