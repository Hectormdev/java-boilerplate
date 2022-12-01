package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.FullName;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.PhoneNumber;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserFinderService;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserContactsUpdater {

    private final IUserRepository userRepository;

    private final IUUIDService uuidService;


    private final UserFinderService userFinderService;


    public UserContactsUpdater(IUserRepository userRepository, IUUIDService uuidService) {
        this.userRepository = userRepository;
        this.uuidService = uuidService;
        this.userFinderService = new UserFinderService(userRepository);

    }

    public User execute(String userId, List<ContactRequestDTO> contactUpdateRequestDTO) throws UserNotFoundError, InvalidNameError, NotAllowedPhoneError {
        User user = this.userFinderService.findByUserId(userId);

        List<Contact> contacts = this.buildContacts(contactUpdateRequestDTO);
        user.updateContacts(contacts);
        return this.userRepository.updateUser(user);
    }


    private List<Contact> buildContacts(List<ContactRequestDTO> requestContacts) throws InvalidNameError, NotAllowedPhoneError {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (ContactRequestDTO requestContact : requestContacts) {
            Contact contact = new Contact(
                    DomainId.fromString(this.uuidService.generateUUID()),
                    FullName.create(requestContact.name(), requestContact.surname()),
                    PhoneNumber.fromString(requestContact.phoneNumber()));
            contacts.add(contact);
        }
        return contacts;
    }

}
