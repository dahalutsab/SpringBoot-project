package com.aadim.project.customExceptions;


public class EmailSendException extends RuntimeException {
    public EmailSendException(String message) {
        super(message);
    }
}