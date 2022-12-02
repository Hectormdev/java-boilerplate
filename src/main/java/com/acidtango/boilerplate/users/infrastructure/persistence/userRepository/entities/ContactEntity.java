package com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.entities;


import com.acidtango.boilerplate.users.domain.Contact;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number_prefix")
    private String phoneNumberPrefix;

    @Column(name = "phone_number_digits")
    private String phoneNumberDigits;

    @ManyToOne(optional = false)
    private UserEntity user;


    public static ContactPrimitives toPrimitives(ContactEntity contactEntity) {
        return new ContactPrimitives(
                contactEntity.contactId,
                new FullNamePrimitives(contactEntity.name, contactEntity.surname),
                new PhoneNumberPrimitives(contactEntity.phoneNumberPrefix, contactEntity.phoneNumberDigits)
        );
    }

    public static ContactEntity fromDomain(UserEntity user, Contact contact) {
        ContactPrimitives contactPrimitives = contact.toPrimitives();
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.contactId = contactPrimitives.contactId();
        contactEntity.name = contactPrimitives.fullName().name();
        contactEntity.surname = contactPrimitives.fullName().surname();
        contactEntity.phoneNumberPrefix = contactPrimitives.phoneNumber().prefix();
        contactEntity.phoneNumberDigits = contactPrimitives.phoneNumber().digits();
        contactEntity.user = user;

        return contactEntity;
    }
}
