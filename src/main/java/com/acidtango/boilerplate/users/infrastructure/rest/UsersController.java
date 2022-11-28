package com.acidtango.boilerplate.users.infrastructure.rest;


import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.application.UserCreator;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.UserResponseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/users")
public class UsersController {


    private UserCreator userCreator;

    public UsersController(
            IUUIDService uuidService,
            IClockService clockService,
            IUserRepository userRepository){
        this.userCreator = new UserCreator(userRepository,uuidService,clockService);
    }

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) throws InvalidNameError, NotAllowedPhoneError {
        User user = this.userCreator.createUser(
                createUserRequestDTO.name(),
                createUserRequestDTO.surname(),
                createUserRequestDTO.phoneNumber());
        UserPrimitives userPrimitives = user.toPrimitives();
        return new UserResponseDTO(userPrimitives.userId(),
                userPrimitives.fullName().name(),
                userPrimitives.fullName().surname(),
                userPrimitives.phoneNumber().prefix()+userPrimitives.phoneNumber().number(),
                userPrimitives.contacts()
        );
    }
}
