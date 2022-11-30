package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.domain.*;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserCreator {

    private final IUserRepository userRepository;
    private final IUUIDService uuidService;
    private final IClockService clockService;


    public UserCreator(IUserRepository userRepository,IUUIDService uuidService,IClockService clockService){
        this.userRepository = userRepository;
        this.uuidService = uuidService;
        this.clockService = clockService;
    }


    public User createUser(CreateUserRequestDTO request)
            throws InvalidNameError, NotAllowedPhoneError {

        List<Contact> contacts = this.buildContacts(request.contacts());

        User user = User.create(
                this.uuidService.generateUUID(),
                request.name(),
                request.surname(),
                request.phoneNumber(),
                contacts,
                this.clockService.getTime()
        );

        this.userRepository.save(user);
        return user;
    }


    private List<Contact> buildContacts(List<ContactRequestDTO> requestContacts) throws InvalidNameError, NotAllowedPhoneError {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (ContactRequestDTO requestContact : requestContacts) {
            Contact contact = new Contact(
                    DomainId.fromString(this.uuidService.generateUUID()),
                    new FullName(requestContact.name(),requestContact.surname()),
                    PhoneNumber.fromString(requestContact.phoneNumber()));
            contacts.add(contact);
        }
        return contacts;
    }
}
