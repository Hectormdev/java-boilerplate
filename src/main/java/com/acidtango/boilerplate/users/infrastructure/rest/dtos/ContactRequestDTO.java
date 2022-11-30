package com.acidtango.boilerplate.users.infrastructure.rest.dtos;

import javax.validation.constraints.NotBlank;

public record ContactRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String phoneNumber
) {

}
