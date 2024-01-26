package com.aadim.project.globalException;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.customExceptions.*;
import com.aadim.project.dto.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends BaseController {

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<GlobalApiResponse> handleUsernameAlreadyTaken(UsernameAlreadyTakenException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<GlobalApiResponse> handleEmailAlreadyTaken(EmailAlreadyTakenException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<GlobalApiResponse> handleRoleNotFound(RoleNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }

    @ExceptionHandler(UserSaveException.class)
    public ResponseEntity<GlobalApiResponse> handleUserSave(UserSaveException ex) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<GlobalApiResponse> handleEmailSend(EmailSendException ex) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
}