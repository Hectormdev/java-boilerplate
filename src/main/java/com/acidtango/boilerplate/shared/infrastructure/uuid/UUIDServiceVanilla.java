package com.acidtango.boilerplate.shared.infrastructure.uuid;

import com.acidtango.boilerplate.shared.domain.IUUIDService;

import java.util.UUID;

public final class UUIDServiceVanilla implements IUUIDService {
    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
