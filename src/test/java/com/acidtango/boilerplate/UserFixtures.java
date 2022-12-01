package com.acidtango.boilerplate;

import com.acidtango.boilerplate.users.domain.User;
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
            new ArrayList<>(List.of(new ContactPrimitives(
                    UUID.randomUUID().toString(),
                    new FullNamePrimitives("Pepe", "Rodríguez"),
                    new PhoneNumberPrimitives("+34", "987654321"))))
    );

    public static final UserPrimitives pabloPrimitives = new UserPrimitives(
            "1cefdad1-1eba-4d87-85aa-922273a63b68",
            new FullNamePrimitives("Pablo", "González"),
            new PhoneNumberPrimitives("+34", "333333333"),
            LocalDateTime.of(2019, Month.JANUARY, 14, 0, 0),
            new ArrayList<>(List.of(new ContactPrimitives(
                    UUID.randomUUID().toString(),
                    new FullNamePrimitives("Juan", "Herrera"),
                    new PhoneNumberPrimitives("+34", "777777777"))))
    );

    public static User pedro() {
        return User.fromPrimitives(UserFixtures.pedroPrimitives);
    }

    public static User pablo() {
        return User.fromPrimitives(UserFixtures.pabloPrimitives);
    }
}
