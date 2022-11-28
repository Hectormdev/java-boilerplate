package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;

public class Contact {
    private DomainId contactId;

    private FullName fullName;

    private PhoneNumber phoneNumber;

    public  Contact(DomainId contactId, FullName fullName, PhoneNumber phoneNumber){
        this.contactId= contactId;
        this.fullName =fullName;
        this.phoneNumber = phoneNumber;
    }

    public static Contact fromPrimitives(ContactPrimitives contactPrimitives)
            throws InvalidNameError, NotAllowedPhoneError {
        DomainId contactId = DomainId.fromString(contactPrimitives.contactId());
        FullName fullName = FullName.fromPrimitives(contactPrimitives.fullName());
        PhoneNumber phoneNumber = PhoneNumber.fromPrimitives(contactPrimitives.phoneNumber());
        Contact contact=  new Contact(contactId,fullName,phoneNumber);
        return contact;
    }

    public ContactPrimitives toPrimitives(){
        return new ContactPrimitives(contactId.toString(),fullName.toPrimitives(),phoneNumber.toPrimitives());
    }
}
