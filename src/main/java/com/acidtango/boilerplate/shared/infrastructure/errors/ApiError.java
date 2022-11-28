package com.acidtango.boilerplate.shared.infrastructure.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class ApiError {

    private static final Map<DomainErrorCode, HttpStatus> domainErrorCodeToHttpStatusMap = new HashMap<>()
    {{
        put(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_USER_NAME, HttpStatus.BAD_REQUEST);
    }};

    private HttpStatus httpStatus;

    private DomainErrorCode domainErrorCode;

    private LocalDateTime timeStamp;

    private String message;


    public ApiError(HttpStatus httpStatus, LocalDateTime timeStamp,DomainErrorCode domainErrorCode, String message){
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.domainErrorCode =domainErrorCode;
        this.message = message;
    }

    public static ApiError create(DomainError domainError){
        return new ApiError(domainErrorCodeToHttpStatusMap.get(domainError.getCode()),LocalDateTime.now(),domainError.getCode(),domainError.getMessage());
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

    public LocalDateTime getTimeStamp(){
        return this.timeStamp;
    }

    public DomainErrorCode getDomainErrorCode() {
        return domainErrorCode;
    }

    public String getMessage() {
        return message;
    }


}
