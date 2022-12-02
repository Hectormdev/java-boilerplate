package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;

import java.util.UUID;

public final class UserId extends DomainId {
    private UserId(UUID userId) {
        super(userId);
    }

    public static UserId fromString(String domainId) {
        return new UserId(UUID.fromString(domainId));
    }

}
