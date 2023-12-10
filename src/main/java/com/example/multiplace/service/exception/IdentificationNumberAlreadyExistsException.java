package com.example.multiplace.service.exception;

public class IdentificationNumberAlreadyExistsException extends RuntimeException {
    public IdentificationNumberAlreadyExistsException(String message) {
        super(message);
    }
}