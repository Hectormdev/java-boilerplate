package com.acidtango.boilerplate.users.infrastructure.rest.dtos;


import javax.validation.constraints.NotBlank;
import java.util.List;

public record CreateUserRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String phoneNumber,

        List<ContactRequestDTO> contacts
){}
