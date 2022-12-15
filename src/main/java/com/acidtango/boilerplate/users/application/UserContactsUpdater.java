package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.IDService;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.ContactId;
import com.acidtango.boilerplate.users.domain.FullName;
import com.acidtango.boilerplate.users.domain.PhoneNumber;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserFinderService;
import com.acidtango.boilerplate.users.domain.UserRepository;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContactsUpdater {

    private final UserRepository userRepository;

    private final IDService iDService;


    private final UserFinderService userFinderService;


    public UserContactsUpdater(UserRepository userRepository, IDService iDService) {
        this.userRepository = userRepository;
        this.iDService = iDService;
        this.userFinderService = new UserFinderService(userRepository);

    }

    public User execute(String userId, List<ContactRequestDTO> contactUpdateRequestDTO) throws DomainError {
        User user = this.userFinderService.findByUserId(userId);

        List<Contact> contacts = this.buildContacts(contactUpdateRequestDTO);
        user.updateContacts(contacts);
        return this.userRepository.updateUser(user);
    }


    private List<Contact> buildContacts(List<ContactRequestDTO> requestContacts) throws DomainError {
        return requestContacts.stream().map((contact) ->
                new Contact(
                        ContactId.fromString(this.iDService.generateID()),
                        FullName.create(contact.name(), contact.surname()),
                        PhoneNumber.fromString(contact.phoneNumber())
                )
        ).toList();
    }

}
