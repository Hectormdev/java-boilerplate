package com.acidtango.boilerplate.shared.infrastructure.uuid;

import com.acidtango.boilerplate.shared.domain.IDService;

import java.util.UUID;

public final class IDServiceVanilla implements IDService {
    @Override
    public String generateID() {
        return UUID.randomUUID().toString();
    }
}
