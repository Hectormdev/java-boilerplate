package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.ddd.ValueObject;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;

import java.util.Objects;

public final class FullName extends ValueObject {
    private static final int MAX_NAME_LENGTH = 144;

    private final String name;

    private final String surname;

    private FullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static FullName create(String name, String surname) throws DomainError {
        if (name.length() + surname.length() > FullName.MAX_NAME_LENGTH) {
            throw new InvalidNameError();
        }
        return new FullName(name, surname);
    }

    public static FullName fromPrimitives(FullNamePrimitives fullNamePrimitives) {
        return new FullName(fullNamePrimitives.name(), fullNamePrimitives.surname());
    }

    public FullNamePrimitives toPrimitives() {
        return new FullNamePrimitives(this.name, this.surname);
    }

    public String getFullName() {
        return name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FullName that)) return false;
        boolean sameName = that.name.equals(this.name);
        boolean sameSurname = that.surname.equals(this.surname);
        return sameName && sameSurname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.surname);
    }
}
