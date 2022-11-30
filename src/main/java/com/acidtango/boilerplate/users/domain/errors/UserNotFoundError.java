package com.acidtango.boilerplate.users.domain.errors;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;

public class UserNotFoundError extends DomainError {
    public UserNotFoundError(String userId) {
        super(DomainErrorCode.USER_NOT_FOUND, "The user with id "+ userId + " was not found");
    }
}
