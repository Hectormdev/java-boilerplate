package com.acidtango.boilerplate.shared.infrastructure.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;


public record ApiError(
        HttpStatus httpStatus,
        long timestamp,
        DomainErrorCode domainErrorCode,
        String message) {

    private static final Map<DomainErrorCode, HttpStatus> DOMAIN_ERROR_CODE_HTTP_STATUS_MAP = new HashMap<>() {{
        put(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_USER_NAME, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }};

    public static ApiError create(DomainError domainError, Long timestamp) {
        return new ApiError(DOMAIN_ERROR_CODE_HTTP_STATUS_MAP.get(
                domainError.getCode()),
                timestamp,
                domainError.getCode(),
                domainError.getMessage());
    }


}
