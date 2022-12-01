package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;

import java.util.Objects;

public class Contact {
    private final DomainId contactId;

    private final FullName fullName;

    private final PhoneNumber phoneNumber;

    public Contact(DomainId contactId, FullName fullName, PhoneNumber phoneNumber) {
        this.contactId = contactId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public static Contact fromPrimitives(ContactPrimitives contactPrimitives) {
        DomainId contactId = DomainId.fromString(contactPrimitives.contactId());
        FullName fullName = FullName.fromPrimitives(contactPrimitives.fullName());
        PhoneNumber phoneNumber = PhoneNumber.fromPrimitives(contactPrimitives.phoneNumber());
        return new Contact(contactId, fullName, phoneNumber);
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public ContactPrimitives toPrimitives() {
        return new ContactPrimitives(contactId.toString(), fullName.toPrimitives(), phoneNumber.toPrimitives());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact that)) return false;
        boolean contactId = this.contactId.equals(that.contactId);
        boolean phoneNumber = this.phoneNumber.equals(that.phoneNumber);
        boolean fullName = this.fullName.equals(that.fullName);
        return contactId && phoneNumber && fullName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contactId.toString());
    }
}
