package com.aadim.project.controller.base;

import com.aadim.project.dto.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BaseController {
    public ResponseEntity<GlobalApiResponse> successResponse(Object data){
        GlobalApiResponse response = new GlobalApiResponse(LocalDateTime.now(),
                HttpStatus.OK.name(),data, "Success");
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<GlobalApiResponse> errorResponse(HttpStatus status, String message, Exception exception) {
        GlobalApiResponse response = new GlobalApiResponse(LocalDateTime.now(),"Failure",  status.name(), null);
        response.setMessage(message);
        if (exception != null) {
            response.setData(exception.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }
}
