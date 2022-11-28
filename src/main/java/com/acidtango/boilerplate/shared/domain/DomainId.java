package com.acidtango.boilerplate.shared.domain;

import java.util.UUID;

public class DomainId {

    private UUID domainId;

    private DomainId(UUID domainId){
        this.domainId =domainId;
    }

    public static DomainId fromString(String domainId){
        return new DomainId(UUID.fromString(domainId));
    }

    @Override
    public String toString(){
        return this.domainId.toString();
    }

    @Override
    public boolean equals(Object domainId) {
        return domainId.toString().equals(this.toString());
    }
}
