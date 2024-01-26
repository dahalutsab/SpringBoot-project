package com.aadim.project.globalException;

import com.aadim.project.controller.base.BaseController;
import com.aadim.project.dto.GlobalApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<GlobalApiResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        return errorResponse(HttpStatus.UNAUTHORIZED, "Token Expired: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalApiResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Wrong argument type: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GlobalApiResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return errorResponse(HttpStatus.METHOD_NOT_ALLOWED, "Method not supported: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GlobalApiResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Missing request parameter: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GlobalApiResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GlobalApiResponse> handleAccessDenied(AccessDeniedException ex) {
        return errorResponse(HttpStatus.FORBIDDEN, "Access denied: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalApiResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return errorResponse(HttpStatus.CONFLICT, "Database error: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalApiResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Not found: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalApiResponse> handleGeneralException(Exception ex) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: " + ex.getMessage(), ex);
    }
}
