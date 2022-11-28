package com.acidtango.boilerplate.users.domain;


import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceFake;
import com.acidtango.boilerplate.users.domain.errors.InvalidNameError;
import com.acidtango.boilerplate.users.domain.errors.NotAllowedPhoneError;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    private final String RANDOM_UUID = UUID.randomUUID().toString();
    private final String RANDOM_CONTACT_UUID = UUID.randomUUID().toString();
    private final String FAKE_NAME = "Pedro";
    private final String FAKE_SURNAME = "Martín";
    private final String FAKE_PHONE = "+34123456789";
    private final LocalDateTime FAKE_TIME = ClockServiceFake.FIXED_FAKE_TIME;


    @Nested
    @DisplayName("Construction of User")
    class UserConstruction {
        @Test
        @DisplayName("Gets Created Successfully")
        public void getsCreatedSuccessfully() throws InvalidNameError, NotAllowedPhoneError {
            User expectedUser = User.create(RANDOM_UUID, FAKE_NAME, FAKE_SURNAME, FAKE_PHONE, FAKE_TIME);
            assertTrue(expectedUser.getUserId().equals(DomainId.fromString(RANDOM_UUID)));
        }

        @Test
        @DisplayName("Throws a invalid Name error if full name is too large")
        public void throwsInvalidNameError() {
            assertThrows(InvalidNameError.class, () -> {
                User.create(RANDOM_UUID, FAKE_NAME.repeat(100), FAKE_SURNAME, FAKE_PHONE, FAKE_TIME);
            });
        }

        @Test
        @DisplayName("Throws an invalid phone number error if phone is not from Spain or Mexico")
        public void throwsInvalidPhoneNumber() {
            assertThrows(NotAllowedPhoneError.class, () -> {
                User.create(RANDOM_UUID, FAKE_NAME, FAKE_SURNAME, "+33123456789", FAKE_TIME);
            });
        }
    };

    @Nested
    @DisplayName("User to Primitives and vice-versa")
    class UserPrimitivesConversion{

        private User user;
        private Contact contact;

        @BeforeEach
        public void init() throws InvalidNameError, NotAllowedPhoneError {
            user = User.create(RANDOM_UUID,FAKE_NAME,FAKE_SURNAME,FAKE_PHONE,FAKE_TIME);
            contact = new Contact(
                    DomainId.fromString(UUID.randomUUID().toString()),
                    FullName.create("Pepe","Rodríguez"),
                    PhoneNumber.fromString("+34987654321"));
            user.addContact(contact);
        }


        @Test
        @DisplayName("converts a user correctly to its primitives")
        public void convertsToUserPrimitives(){
            UserPrimitives userPrimitives = user.toPrimitives();

            assertEquals(userPrimitives.userId(),RANDOM_UUID);
            assertEquals(userPrimitives.fullName(), new FullNamePrimitives(FAKE_NAME,FAKE_SURNAME));
            assertEquals(userPrimitives.phoneNumber(), new PhoneNumberPrimitives("+34","123456789"));
            assertEquals(userPrimitives.createdAt(),FAKE_TIME);
            assertEquals(userPrimitives.contacts().size(),1);
            assertEquals(userPrimitives.contacts().get(0).fullName().name(),"Pepe");
        }

        @Test
        @DisplayName("creates a user correctly from primitives")
        public void convertsFromUserPrimitives() throws InvalidNameError, NotAllowedPhoneError {
            ArrayList<ContactPrimitives> contacts = new ArrayList<>();
            contacts.add(contact.toPrimitives());

            UserPrimitives userPrimitives = new UserPrimitives(
                    RANDOM_UUID,
                    new FullNamePrimitives(FAKE_NAME,FAKE_SURNAME),
                    new PhoneNumberPrimitives("+34","123456789"),
                    FAKE_TIME,
                    new ArrayList<>()
                    );
            User builtUser = User.fromPrimitives(userPrimitives);
            assertTrue(builtUser.equals(user));

        }
    }
}
