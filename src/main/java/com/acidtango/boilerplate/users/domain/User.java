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

    public List<Contact> getContacts(){
        return this.contacts;
    }

    public static User create(String userId, String name, String surname, String phoneNumber, LocalDateTime createdAt,List<Contact> contacts)
            throws InvalidNameError, NotAllowedPhoneError {
        return new User(
                DomainId.fromString(userId),
                new FullName(name,surname),
                PhoneNumber.fromString(phoneNumber),
                contacts,
                createdAt);
    }

    public PhoneNumber getPhoneNumber(){
        return this.phoneNumber;
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
        if(!(o instanceof User)) return false;
        User that = (User)o;
        boolean userId = this.userId.equals(that.userId);
        boolean phoneNumber = this.phoneNumber.equals(that.phoneNumber);
        boolean fullName = this.fullName.equals(that.fullName);
        boolean createdAt = this.createdAt == that.createdAt;
        boolean contacts = this.contacts.equals(that.contacts);
        return userId & phoneNumber & fullName & createdAt & contacts;
    }

    public DomainId getUserId() {
        return this.userId;
    }


}
