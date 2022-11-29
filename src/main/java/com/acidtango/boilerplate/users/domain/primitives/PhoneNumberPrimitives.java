package com.acidtango.boilerplate.users.domain.primitives;

public record PhoneNumberPrimitives(String prefix, String number) {

    @Override
    public String toString(){
        return this.prefix+this.number;
    }
}
