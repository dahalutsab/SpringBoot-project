package com.aadim.project.controller.base;

import com.aadim.project.dto.response.GlobalAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BaseController {
    public ResponseEntity<GlobalAPIResponse> successResponse(Object data) {
        GlobalAPIResponse response = new GlobalAPIResponse("Success", LocalDateTime.now(), HttpStatus.OK.name(), data);
        return ResponseEntity.ok(response);
    }
}
