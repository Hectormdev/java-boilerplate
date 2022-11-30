package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;

import java.util.Arrays;
import java.util.Objects;

public final class PhoneNumber {

    private static final String[] ALLOWED_CODES = {"+34", "+52"};

    private final String prefix;

    private final String digits;


    private PhoneNumber(String prefix, String digits) {
        this.prefix = prefix;
        this.digits = digits;
    }

    public static PhoneNumber fromString(String number) throws NotAllowedPhoneError {
        final int prefixDigits = 3;

        String prefix = number.substring(0, prefixDigits);
        String phoneNumber = number.substring(prefixDigits);
        if (!Arrays.asList(ALLOWED_CODES).contains(prefix)) {
            throw new NotAllowedPhoneError();
        }
        return new PhoneNumber(prefix, phoneNumber);
    }

    public static PhoneNumber fromPrimitives(PhoneNumberPrimitives phoneNumberPrimitives) {
        return new PhoneNumber(phoneNumberPrimitives.prefix(), phoneNumberPrimitives.digits());
    }

    public PhoneNumberPrimitives toPrimitives() {
        return new PhoneNumberPrimitives(prefix, digits);
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneNumber that)) return false;
        return (this.prefix.equals(that.prefix) && this.digits.equals(that.digits));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.prefix, this.digits);
    }

    @Override
    public String toString() {
        return this.prefix + this.digits;
    }
}
