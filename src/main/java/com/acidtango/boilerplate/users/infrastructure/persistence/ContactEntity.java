package com.acidtango.boilerplate.users.infrastructure.persistence;


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



    public static ContactEntity fromDomain(UserEntity user, ContactPrimitives contact){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.contactId = contact.contactId();
        contactEntity.name = contact.fullName().name();
        contactEntity.surname = contact.fullName().surname();
        contactEntity.phoneNumber = contact.phoneNumber().prefix()+contact.phoneNumber().number();
        contactEntity.user = user;

        return contactEntity;
    }
}
