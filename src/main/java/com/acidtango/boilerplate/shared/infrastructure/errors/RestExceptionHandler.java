package com.acidtango.boilerplate.shared.infrastructure.errors;


import com.acidtango.boilerplate.shared.domain.DomainError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainError.class)
    public final ResponseEntity<ApiError> handleDomainException(DomainError domainError, WebRequest request) {
        ApiError apiError = ApiError.create(domainError);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }





}
