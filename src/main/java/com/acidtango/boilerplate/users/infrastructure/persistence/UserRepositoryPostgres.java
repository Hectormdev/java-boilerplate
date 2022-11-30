package com.acidtango.boilerplate.users.infrastructure.persistence;


import com.acidtango.boilerplate.shared.domain.DomainId;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class UserRepositoryPostgres implements IUserRepository {

    @Autowired
    UserRepositoryJPA repository;

    @Override
    public void save(User user) {
        this.repository.save(UserEntity.fromDomain(user));

    }

    @Override
    public Optional<User> findByUserId(DomainId userId) {
        Optional<UserEntity> userEntity =  this.repository.findById(userId.toString());
        if(userEntity.isEmpty()) Optional.empty();
        return Optional.of(userEntity.get().toDomain());
    }
}
