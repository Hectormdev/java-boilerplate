package com.acidtango.boilerplate.users.domain.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;

public class NotAllowedPhoneError extends DomainError {

    public NotAllowedPhoneError() {
        super(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR, "The phone number that was given is invalid");
    }
}
