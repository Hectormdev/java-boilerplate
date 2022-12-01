package com.acidtango.boilerplate.users.infrastructure.rest.dtos;

import com.acidtango.boilerplate.users.domain.Contact;

import java.util.List;

public record CommonContactResponseDTO(List<ContactResponseDTO> commonContacts) {
    public static CommonContactResponseDTO fromDomain(List<Contact> contacts) {
        return new CommonContactResponseDTO(contacts.stream().map(ContactResponseDTO::fromDomain).toList());
    }

}
