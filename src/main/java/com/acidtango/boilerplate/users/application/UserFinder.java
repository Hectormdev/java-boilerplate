package com.acidtango.boilerplate.users.application;


import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserFinderService;
import com.acidtango.boilerplate.users.domain.UserRepository;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import org.springframework.stereotype.Service;


@Service
public class UserFinder {


    private final UserFinderService userFinderService;


    public UserFinder(UserRepository userRepository) {
        this.userFinderService = new UserFinderService(userRepository);

    }

    public User execute(String userId) throws UserNotFoundError {
        return this.userFinderService.findByUserId(userId);
    }
}
