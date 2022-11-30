package com.acidtango.boilerplate.shared.domain;

import java.util.Objects;
import java.util.UUID;

public final class DomainId {

    private final UUID domainId;

    private DomainId(UUID domainId) {
        this.domainId = domainId;
    }

    public static DomainId fromString(String domainId) {
        return new DomainId(UUID.fromString(domainId));
    }

    @Override
    public String toString() {
        return this.domainId.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DomainId that)) return false;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.domainId);
    }
}
