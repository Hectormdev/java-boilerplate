package com.acidtango.boilerplate.shared.infrastructure.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class ApiError {

    private static final Map<DomainErrorCode, HttpStatus> domainErrorCodeToHttpStatusMap = new HashMap<>()
    {{
        put(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_USER_NAME, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }};

    private HttpStatus httpStatus;

    private DomainErrorCode domainErrorCode;

    private long timestamp;

    private String message;


    public ApiError(HttpStatus httpStatus, long timestamp, DomainErrorCode domainErrorCode, String message){
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.domainErrorCode =domainErrorCode;
        this.message = message;
    }

    public static ApiError create(DomainError domainError,Long timestamp){
        return new ApiError(domainErrorCodeToHttpStatusMap.get(
                domainError.getCode()),
                timestamp,
                domainError.getCode(),
                domainError.getMessage());
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

    public long getTimestamp(){
        return this.timestamp;
    }

    public DomainErrorCode getDomainErrorCode() {
        return domainErrorCode;
    }

    public String getMessage() {
        return message;
    }


}
