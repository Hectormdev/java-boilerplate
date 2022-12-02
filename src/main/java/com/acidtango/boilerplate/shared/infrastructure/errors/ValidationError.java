package com.acidtango.boilerplate.shared.infrastructure.errors;


import java.time.LocalDateTime;
import java.util.List;

public record ValidationError(String message, List<String> constraintViolations, LocalDateTime timestamp) {

    public static ValidationError create(List<String> constraintViolations, LocalDateTime timestamp) {
        return new ValidationError("DTO Is not correct", constraintViolations, timestamp);
    }

}
