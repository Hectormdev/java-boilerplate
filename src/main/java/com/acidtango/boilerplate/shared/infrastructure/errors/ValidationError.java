package com.acidtango.boilerplate.shared.infrastructure.errors;


import java.util.List;

public record ValidationError(String message, List<String> constraintViolations, long timestamp) {

    public static ValidationError create(List<String> constraintViolations, long timestamp) {
        return new ValidationError("DTO Is not correct", constraintViolations, timestamp);
    }

}
