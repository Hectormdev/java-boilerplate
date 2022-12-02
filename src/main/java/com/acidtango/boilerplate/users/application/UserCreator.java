package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.ClockService;
import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.IDService;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.ContactId;
import com.acidtango.boilerplate.users.domain.FullName;
import com.acidtango.boilerplate.users.domain.PhoneNumber;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserId;
import com.acidtango.boilerplate.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserCreator {

    private final UserRepository userRepository;
    private final IDService iDService;
    private final ClockService clockService;

    public UserCreator(UserRepository userRepository, IDService iDService, ClockService clockService) {
        this.userRepository = userRepository;
        this.iDService = iDService;
        this.clockService = clockService;
    }

    public User execute(String name, String surname, String phoneNumber, List<ContactRequest> contactsRequest)
            throws DomainError {

        List<Contact> contacts = this.buildContacts(contactsRequest);

        User user = new User(
                UserId.fromString(this.iDService.generateID()),
                FullName.create(name, surname),
                PhoneNumber.fromString(phoneNumber),
                contacts,
                this.clockService.getTime()
        );

        this.userRepository.save(user);
        return user;
    }

    private List<Contact> buildContacts(List<ContactRequest> requestContacts) {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (ContactRequest requestContact : requestContacts) {
            Contact contact = new Contact(
                    ContactId.fromString(this.iDService.generateID()),
                    requestContact.fullName,
                    requestContact.phoneNumber);
            contacts.add(contact);
        }
        return contacts;
    }

    public static final class ContactRequest {
        private final FullName fullName;
        private final PhoneNumber phoneNumber;

        public ContactRequest(String name, String surname, String phoneNumber) throws DomainError {
            this.fullName = FullName.create(name, surname);
            this.phoneNumber = PhoneNumber.fromString(phoneNumber);
        }
    }
}
