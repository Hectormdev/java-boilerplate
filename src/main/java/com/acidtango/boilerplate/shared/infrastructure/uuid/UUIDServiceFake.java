package com.acidtango.boilerplate.shared.infrastructure.uuid;

import com.acidtango.boilerplate.shared.domain.IUUIDService;

public class UUIDServiceFake implements IUUIDService {

    private String fixedUUID;

    public UUIDServiceFake(String fixedUUID){
        this.fixedUUID = fixedUUID;
    }

    @Override
    public String generateUUID() {
        return fixedUUID;
    }
}
