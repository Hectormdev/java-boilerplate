package com.acidtango.boilerplate.users.domain;


import com.acidtango.boilerplate.shared.domain.DomainError;
import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceFake;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserTest {

    private final String RANDOM_UUID = UUID.randomUUID().toString();
    private final String FAKE_NAME = "Pedro";
    private final String FAKE_SURNAME = "Martín";
    private final String FAKE_PHONE = "+34123456789";
    private final ArrayList<Contact> FAKE_CONTACTS = new ArrayList<>();
    private final LocalDateTime FAKE_TIME = ClockServiceFake.FIXED_FAKE_TIME;


    @Nested
    class user_construction {
        @Test
        public void gets_created_successfully() throws DomainError {
            User expectedUser = User.create(RANDOM_UUID, FAKE_NAME, FAKE_SURNAME, FAKE_PHONE, FAKE_CONTACTS, FAKE_TIME);
            assertEquals(expectedUser.getUserId(), DomainId.fromString(RANDOM_UUID));
        }

        @Test
        public void throws_invalid_name_error() {
            assertThrows(InvalidNameError.class, () -> {
                User.create(RANDOM_UUID, FAKE_NAME.repeat(100), FAKE_SURNAME, FAKE_PHONE, FAKE_CONTACTS, FAKE_TIME);
            });
        }

        @Test
        public void throws_invalid_phone_number_error() {
            assertThrows(NotAllowedPhoneError.class, () -> {
                User.create(RANDOM_UUID, FAKE_NAME, FAKE_SURNAME, "+33123456789", FAKE_CONTACTS, FAKE_TIME);
            });
        }
    }
}
