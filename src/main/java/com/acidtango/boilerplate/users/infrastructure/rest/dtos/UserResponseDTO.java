package com.acidtango.boilerplate.users.infrastructure.rest.dtos;

import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;

import java.util.List;

public record UserResponseDTO(String userId, String name, String surname, String phoneNumber, List<ContactPrimitives> contacts){
}
