package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;

import java.util.Arrays;

public class PhoneNumber {

    private final static String[] ALLOWED_CODES = {"+34","+52"};

    private String prefix;

    private String digits;


    private PhoneNumber(String prefix, String digits)  {
        this.prefix = prefix;
        this.digits = digits;
    }

    public static PhoneNumber fromString(String number) throws NotAllowedPhoneError {
        String prefix = number.substring(0,3);
        String phoneNumber = number.substring(3);
        if (!Arrays.asList(ALLOWED_CODES).contains(prefix)){
            throw new NotAllowedPhoneError();
        }
        return new PhoneNumber(prefix,phoneNumber);
    }

    public static PhoneNumber fromPrimitives(PhoneNumberPrimitives phoneNumberPrimitives)  {
        return new PhoneNumber(phoneNumberPrimitives.prefix(),phoneNumberPrimitives.digits());
    }

    public PhoneNumberPrimitives toPrimitives(){
        return new PhoneNumberPrimitives(prefix, digits);
    }


    @Override
    public boolean equals(Object o){
        if(!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return (this.prefix == that.prefix && this.digits == that.digits);
    }

    @Override
    public String toString(){
        return this.prefix+this.digits;
    }
}
