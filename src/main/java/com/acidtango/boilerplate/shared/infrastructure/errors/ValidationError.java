package com.acidtango.boilerplate.shared.infrastructure.errors;

import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationError {

    private long timestamp;
    private String message;
    private List<String> constraintViolations;
    public ValidationError(String message,List<String> constraintViolations, long timestamp ){
        this.timestamp = timestamp;
        this.message = message;
        this.constraintViolations = constraintViolations;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public List<String> getConstraintViolations() {
        return this.constraintViolations;
    }

    public static ValidationError create(List<String> constraintViolations, long timestamp){
        return new ValidationError("DTO Is not correct",constraintViolations,timestamp);
    }

    public String getMessage() {
        return message;
    }

}
