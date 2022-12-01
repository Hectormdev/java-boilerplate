package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserFinderService;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonContactFinder {


    private final UserFinderService userFinderService;


    public CommonContactFinder(IUserRepository userRepository) {
        this.userFinderService = new UserFinderService(userRepository);
    }

    public List<Contact> execute(String firstUserId, String secondUserId) throws UserNotFoundError {
        User firstUser = this.userFinderService.findByUserId(firstUserId);
        User secondUser = this.userFinderService.findByUserId(secondUserId);

        return firstUser.getCommonContacts(secondUser);
    }
}
