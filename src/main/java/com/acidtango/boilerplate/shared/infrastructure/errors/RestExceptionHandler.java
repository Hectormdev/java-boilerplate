package com.acidtango.boilerplate.shared.infrastructure.errors;


import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.IClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    IClockService clockService;

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException validationError, HttpHeaders headers, HttpStatus status, WebRequest request){

        ValidationError error =  ValidationError.create(
                validationError.getFieldErrors().stream().map(e->e.getField()).toList(),
                this.clockService.getTimeStamp());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainError.class)
    public final ResponseEntity<ApiError> handleDomainException(DomainError domainError) {
        ApiError apiError = ApiError.create(domainError,this.clockService.getTimeStamp());
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }





}
