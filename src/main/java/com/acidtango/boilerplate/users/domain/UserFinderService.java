package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;

import java.util.Optional;

public class UserFinderService {


    private final IUserRepository userRepository;

    public UserFinderService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(String userId) throws UserNotFoundError {
        Optional<User> user = this.userRepository.findByUserId(DomainId.fromString(userId));
        if (user.isEmpty()) throw new UserNotFoundError(userId);

        return user.get();
    }
}
