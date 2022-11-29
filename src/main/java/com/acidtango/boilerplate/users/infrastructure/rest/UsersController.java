package com.acidtango.boilerplate.users.infrastructure.rest;


import com.acidtango.boilerplate.users.application.UserCreator;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.UserResponseDTO;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    private UserCreator userCreator;


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) throws InvalidNameError, NotAllowedPhoneError {
        User user = this.userCreator.createUser(
                createUserRequestDTO.name(),
                createUserRequestDTO.surname(),
                createUserRequestDTO.phoneNumber(),
                new ArrayList<>()
                );
        UserPrimitives userPrimitives = user.toPrimitives();
        return new UserResponseDTO(userPrimitives.userId(),
                userPrimitives.fullName().name(),
                userPrimitives.fullName().surname(),
                user.getPhoneNumber().toString(),
                userPrimitives.contacts()
        );
    }
}
