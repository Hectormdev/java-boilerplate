package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;

public class FullName {
    private final static int MAX_NAME_LENGTH = 144;

    private String name;

    private String surname;

    public FullName(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public static  FullName create(String name,String surname) throws InvalidNameError {
        if(name.length()+surname.length()>FullName.MAX_NAME_LENGTH ){
            throw new InvalidNameError();
        }
        return new FullName(name,surname);
    }

    public static FullName fromPrimitives(FullNamePrimitives fullNamePrimitives) throws InvalidNameError {
        return FullName.create(fullNamePrimitives.name(),fullNamePrimitives.surname());
    }

    public FullNamePrimitives toPrimitives(){
        return new FullNamePrimitives(this.name,this.surname);
    }

    public String getFullName(){
        return name + " " + surname;
    }
}
