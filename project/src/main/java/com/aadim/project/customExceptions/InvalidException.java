package com.aadim.project.customExceptions;

public class InvalidException extends RuntimeException {
  public InvalidException(String message) {
    super(message);
  }
}
