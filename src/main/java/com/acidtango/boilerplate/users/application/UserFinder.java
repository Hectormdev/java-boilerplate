package com.acidtango.boilerplate.users.application;


import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFinder {

    private final IUserRepository userRepository;


    public UserFinder(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String userId) throws UserNotFoundError {
        Optional<User> user = this.userRepository.findByUserId(DomainId.fromString(userId));
        if (user.isEmpty()) throw new UserNotFoundError(userId);

        return user.get();
    }
}
