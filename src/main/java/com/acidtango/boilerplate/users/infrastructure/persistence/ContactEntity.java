package com.acidtango.boilerplate.users.infrastructure.persistence;


import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;

import javax.persistence.*;


@Entity
@Table(name="contacts")
public class ContactEntity {

    @Id
    @Column(name="contact_id")
    private String contactId;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone_number")
    private String phoneNumber;

    @ManyToOne()
    private UserEntity user;



    public static ContactEntity fromDomain(UserEntity user, Contact contact){
        ContactPrimitives contactPrimitives = contact.toPrimitives();
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.contactId = contactPrimitives.contactId();
        contactEntity.name = contactPrimitives.fullName().name();
        contactEntity.surname = contactPrimitives.fullName().surname();
        contactEntity.phoneNumber = contact.getPhoneNumber().toString();
        contactEntity.user = user;

        return contactEntity;
    }
}
