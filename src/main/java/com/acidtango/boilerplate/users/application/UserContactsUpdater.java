package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.FullName;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.PhoneNumber;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.errors.UserNotFoundError;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserContactsUpdater {

    private final IUserRepository userRepository;

    private final IUUIDService uuidService;


    public UserContactsUpdater(IUserRepository userRepository, IUUIDService uuidService) {
        this.userRepository = userRepository;
        this.uuidService = uuidService;
    }

    public User execute(String userId, List<ContactRequestDTO> contactUpdateRequestDTO) throws UserNotFoundError, InvalidNameError, NotAllowedPhoneError {
        Optional<User> retrievedUser = this.userRepository.findByUserId(DomainId.fromString(userId));
        if (retrievedUser.isEmpty()) throw new UserNotFoundError(userId);

        User user = retrievedUser.get();
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
