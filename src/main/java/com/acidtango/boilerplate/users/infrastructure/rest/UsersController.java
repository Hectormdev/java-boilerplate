package com.acidtango.boilerplate.users.infrastructure.rest;


import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.users.application.CommonContactFinder;
import com.acidtango.boilerplate.users.application.UserContactsUpdater;
import com.acidtango.boilerplate.users.application.UserCreator;
import com.acidtango.boilerplate.users.application.UserFinder;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CommonContactResponseDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UserCreator userCreator;
    private final UserFinder userFinder;
    private final CommonContactFinder commonContactFinder;
    private final UserContactsUpdater userContactsUpdater;

    public UsersController(UserCreator userCreator,
                           UserFinder userFinder,
                           CommonContactFinder commonContactFinder,
                           UserContactsUpdater userContactsUpdater) {
        this.userCreator = userCreator;
        this.commonContactFinder = commonContactFinder;
        this.userContactsUpdater = userContactsUpdater;
        this.userFinder = userFinder;
    }


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO)
            throws DomainError {

        List<UserCreator.ContactRequest> contacts = createUserRequestDTO.contacts()
                .stream().map(
                        (contact) -> new UserCreator.ContactRequest(
                                contact.name(),
                                contact.surname(),
                                contact.phoneNumber()
                        )
                ).toList();

        User user = this.userCreator.execute(
                createUserRequestDTO.name(),
                createUserRequestDTO.surname(),
                createUserRequestDTO.phoneNumber(),
                contacts);


        return UserResponseDTO.fromDomain(user);

    }

    @Transactional
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable("userId") String userId) throws DomainError {
        User user = this.userFinder.execute(userId);

        return UserResponseDTO.fromDomain(user);
    }

    @Transactional
    @GetMapping("/{firstUserId}/common-contacts/{secondUserId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonContactResponseDTO getUser(@PathVariable("firstUserId") String firstUserId, @PathVariable("secondUserId") String secondUserId)
            throws DomainError {
        List<Contact> commonContacts = this.commonContactFinder.execute(firstUserId, secondUserId);

        return CommonContactResponseDTO.fromDomain(commonContacts);
    }

    @Transactional
    @PutMapping("/{userId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateContacts(
            @PathVariable("userId") String userId,
            @Valid @RequestBody List<ContactRequestDTO> contactUpdateRequestDTO)
            throws DomainError {

        User user = this.userContactsUpdater.execute(userId, contactUpdateRequestDTO);
        return UserResponseDTO.fromDomain(user);
    }
}
