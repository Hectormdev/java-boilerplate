package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;

import java.util.Arrays;

public class PhoneNumber {

    private final static String[] ALLOWED_CODES = {"+34","+52"};

    private String prefix;

    private String number;


    private PhoneNumber(String prefix, String number) throws NotAllowedPhoneError {
        if (!Arrays.asList(ALLOWED_CODES).contains(prefix)){
            throw new NotAllowedPhoneError();
        }
        this.prefix = prefix;
        this.number = number;
    }

    public static PhoneNumber fromString(String number) throws NotAllowedPhoneError {
        String prefix = number.substring(0,3);
        String phoneNumber = number.substring(3);
        return new PhoneNumber(prefix,phoneNumber);
    }

    public static PhoneNumber fromPrimitives(PhoneNumberPrimitives phoneNumberPrimitives) throws NotAllowedPhoneError {
        return new PhoneNumber(phoneNumberPrimitives.prefix(),phoneNumberPrimitives.number());
    }

    public PhoneNumberPrimitives toPrimitives(){
        return new PhoneNumberPrimitives(prefix,number);
    }

}
