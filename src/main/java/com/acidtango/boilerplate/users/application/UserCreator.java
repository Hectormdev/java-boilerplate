package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;


public class UserCreator {


    private IUserRepository userRepository;
    private IUUIDService uuidService;
    private IClockService clockService;


    public UserCreator(IUserRepository userRepository,IUUIDService uuidService,IClockService clockService){
        this.userRepository = userRepository;
        this.uuidService = uuidService;
        this.clockService = clockService;
    }


    public User createUser(String name, String surname, String phoneNumber)
            throws InvalidNameError, NotAllowedPhoneError {

        User user = User.create(
                this.uuidService.generateUUID(),
                name,
                surname,
                phoneNumber,
                this.clockService.getTime()
        );

        this.userRepository.save(user);
        return user;
    }
}
