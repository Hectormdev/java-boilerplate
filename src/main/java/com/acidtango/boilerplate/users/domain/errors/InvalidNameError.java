package com.acidtango.boilerplate.users.domain.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;

public class InvalidNameError extends DomainError {
    public InvalidNameError() {
        super(DomainErrorCode.INVALID_USER_NAME, "The given name is not valid");
    }
}
