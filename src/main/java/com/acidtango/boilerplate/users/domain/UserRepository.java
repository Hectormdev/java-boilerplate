package com.acidtango.boilerplate.users.domain;

import com.acidtango.boilerplate.shared.domain.DomainId;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByUserId(DomainId userId);

    User updateUser(User user);
}
