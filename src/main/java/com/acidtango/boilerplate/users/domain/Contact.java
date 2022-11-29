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

    public PhoneNumber getPhoneNumber(){
        return this.phoneNumber;
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

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Contact)) return false;
        Contact that = (Contact)o;
        boolean contactId = this.contactId.equals(that.contactId);
        boolean phoneNumber = this.phoneNumber.equals(that.phoneNumber);
        boolean fullName = this.fullName.equals(that.fullName);
        return contactId && phoneNumber && fullName;
    }
}
