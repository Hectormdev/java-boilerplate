package com.acidtango.boilerplate.users.infrastructure.rest.dtos;

import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;

public record ContactResponseDTO(String contactId, String name, String surname, String phoneNumber) {


    public static ContactResponseDTO fromDomain(Contact contact) {
        ContactPrimitives contactPrimitives = contact.toPrimitives();
        return new ContactResponseDTO(
                contactPrimitives.contactId(),
                contactPrimitives.fullName().name(),
                contactPrimitives.fullName().surname(),
                contact.getPhoneNumber().toString());
    }
}
