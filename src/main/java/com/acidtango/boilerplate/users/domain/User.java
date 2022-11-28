package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private DomainId userId;

    private FullName fullName;

    private PhoneNumber phoneNumber;

    private List<Contact> contacts;

    private LocalDateTime createdAt;


    public User(DomainId userId, FullName fullName, PhoneNumber phoneNumber, List<Contact> contacts, LocalDateTime createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.contacts = contacts;
        this.createdAt = createdAt;
    }

    public void addContact(Contact contact){
        this.contacts.add(contact);
    }

    public static User create(String userId, String name, String surname, String phoneNumber, LocalDateTime createdAt)
            throws InvalidNameError, NotAllowedPhoneError {
        return new User(
                DomainId.fromString(userId),
                FullName.create(name,surname),
                PhoneNumber.fromString(phoneNumber),
                new ArrayList<>(),
                createdAt);
    }


    public static User fromPrimitives(UserPrimitives userPrimitives) throws InvalidNameError, NotAllowedPhoneError {
        List<Contact> contacts = new ArrayList<>();
        for (ContactPrimitives contactPrimitives: userPrimitives.contacts()) {
            contacts.add(Contact.fromPrimitives(contactPrimitives));
        }

        return new User( DomainId.fromString(userPrimitives.userId()),
                FullName.fromPrimitives(userPrimitives.fullName()),
                PhoneNumber.fromPrimitives(userPrimitives.phoneNumber()),
                contacts,
                userPrimitives.createdAt());
    }

    public UserPrimitives toPrimitives(){
        return new UserPrimitives(
                this.userId.toString(),
                this.fullName.toPrimitives(),
                this.phoneNumber.toPrimitives(),
                createdAt,
                contacts.stream().map(Contact::toPrimitives).toList());
    }


    @Override
    public boolean equals(Object o){
        // TODO: Make a better equals
        if(o.getClass() != this.getClass()) return false;
        return ((User) o).userId.equals(this.userId);
    }

    public DomainId getUserId() {
        return this.userId;
    }


}
