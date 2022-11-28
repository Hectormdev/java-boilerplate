package com.acidtango.boilerplate.shared.infrastructure.errors;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationError {

    private LocalDateTime timeStamp;
    private String message;

    private List<String> constraintViolations;
    public ValidationError(LocalDateTime timeStamp, String message,List<String> constraintViolations){
        this.timeStamp = timeStamp;
        this.message = message;
        this.constraintViolations = constraintViolations;
    }
}
