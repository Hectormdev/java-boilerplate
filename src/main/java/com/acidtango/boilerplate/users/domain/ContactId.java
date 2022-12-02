package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;

import java.util.UUID;

public final class ContactId extends DomainId {

    private ContactId(UUID contactId) {
        super(contactId);
    }

    public static ContactId fromString(String domainId) {
        return new ContactId(UUID.fromString(domainId));
    }

}
