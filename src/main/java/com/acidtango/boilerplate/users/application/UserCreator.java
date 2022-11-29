package com.acidtango.boilerplate.users.application;

import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


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


    public User createUser(String name, String surname, String phoneNumber, ArrayList<ContactPrimitives> contactsPrimitives)
            throws InvalidNameError, NotAllowedPhoneError {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (ContactPrimitives contactsPrimitive : contactsPrimitives) {
            Contact contact = Contact.fromPrimitives(contactsPrimitive);
            contacts.add(contact);
        }

        User user = User.create(
                this.uuidService.generateUUID(),
                name,
                surname,
                phoneNumber,
                this.clockService.getTime(),
                contacts
        );

        this.userRepository.save(user);
        return user;
    }
}
