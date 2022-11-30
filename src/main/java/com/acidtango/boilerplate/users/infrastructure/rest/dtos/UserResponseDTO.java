package com.acidtango.boilerplate.users.infrastructure.rest.dtos;

import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(String userId, String name, String surname, String phoneNumber,
                              List<ContactResponseDTO> contacts,
                              LocalDateTime createdAt) {

    public static UserResponseDTO fromDomain(User user) {
        UserPrimitives userPrimitives = user.toPrimitives();
        return new UserResponseDTO(userPrimitives.userId(),
                userPrimitives.fullName().name(),
                userPrimitives.fullName().surname(),
                user.getPhoneNumber().toString(),
                user.getContacts().stream().map(ContactResponseDTO::fromDomain).toList(),
                userPrimitives.createdAt()
        );
    }
}
