package com.acidtango.boilerplate.users.infrastructure.persistence.userRepository;


import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.UserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.entities.UserEntity;

import java.util.Optional;

public class UserRepositoryPostgres implements UserRepository {


    private final UserRepositoryJPA repository;

    public UserRepositoryPostgres(UserRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        this.repository.save(UserEntity.fromDomain(user));
    }

    @Override
    public Optional<User> findByUserId(DomainId userId) {
        Optional<UserEntity> userEntity = this.repository.findById(userId.toString());
        if (userEntity.isEmpty()) return Optional.empty();
        return Optional.of(userEntity.get().toDomain());
    }

    @Override
    public User updateUser(User user) {
        UserEntity updatedUser = this.repository.save(UserEntity.fromDomain(user));
        return updatedUser.toDomain();

    }
}
