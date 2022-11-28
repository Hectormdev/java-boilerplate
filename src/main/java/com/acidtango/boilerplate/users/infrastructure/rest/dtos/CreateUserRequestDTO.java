package com.acidtango.boilerplate.users.infrastructure.rest.dtos;


import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String phoneNumber){}
