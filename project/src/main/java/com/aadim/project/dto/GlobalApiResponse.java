package com.aadim.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalApiResponse {
    private LocalDateTime timestamp;
    private String message;
    private Object data;
    private String status;
}
