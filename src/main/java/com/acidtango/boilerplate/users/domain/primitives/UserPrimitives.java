package com.acidtango.boilerplate.users.domain.primitives;

import java.time.LocalDateTime;
import java.util.List;

public record UserPrimitives(String userId, FullNamePrimitives fullName, PhoneNumberPrimitives phoneNumber,
                             LocalDateTime createdAt, List<ContactPrimitives> contacts) {
}
