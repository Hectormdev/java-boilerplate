package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;

import java.util.Optional;

public class UserFinderService {


    private final UserRepository userRepository;

    public UserFinderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(String userId) throws UserNotFoundError {
        Optional<User> user = this.userRepository.findByUserId(DomainId.fromString(userId));
        return user.orElseThrow(() -> new UserNotFoundError(userId));

    }
}
