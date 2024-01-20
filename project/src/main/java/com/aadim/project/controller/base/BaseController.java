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
}
