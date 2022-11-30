package com.acidtango.boilerplate.users.infrastructure.persistence;

import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryMemory implements IUserRepository {

    private final ArrayList<User> users;

    public UserRepositoryMemory() {
        this.users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        this.users.add(user);
    }

    @Override
    public Optional<User> findByUserId(DomainId userId) {
        return this.users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }
}
