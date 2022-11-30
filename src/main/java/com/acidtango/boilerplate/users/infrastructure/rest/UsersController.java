package com.acidtango.boilerplate.users.infrastructure.rest;


import com.acidtango.boilerplate.users.application.UserCreator;
import com.acidtango.boilerplate.users.application.UserFinder;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private UserFinder userFinder;


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) throws InvalidNameError, NotAllowedPhoneError {
        User user = this.userCreator.createUser(createUserRequestDTO);

        return UserResponseDTO.fromDomain(user);

    }

    @Transactional
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable("userId") String userId) throws UserNotFoundError {
        User user = this.userFinder.findByUserId(userId);

        return UserResponseDTO.fromDomain(user);
    }
}
