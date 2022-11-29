package com.acidtango.boilerplate;

import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFixtures {


    public static final UserPrimitives pedroPrimitives = new UserPrimitives(
            "ed494c20-d701-4bd8-b318-cedd14a7bcc4",
            new FullNamePrimitives("Pedro", "Martín"),
            new PhoneNumberPrimitives("+34", "123456789"),
            LocalDateTime.of(2020, Month.MARCH, 12, 0, 0),
            new ArrayList<ContactPrimitives>(List.of(new ContactPrimitives(
                    UUID.randomUUID().toString(),
                    new FullNamePrimitives("Pepe","Rodríguez"),
                    new PhoneNumberPrimitives("+34","987654321"))))
    );

    public static User pedro() throws InvalidNameError, NotAllowedPhoneError {
        return User.fromPrimitives(UserFixtures.pedroPrimitives);
    }
}
