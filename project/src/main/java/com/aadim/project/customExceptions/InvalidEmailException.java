package com.aadim.project.customExceptions;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(String message) {
    super(message);
  }
}
