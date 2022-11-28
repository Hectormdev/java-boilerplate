package com.acidtango.boilerplate.users.infrastructure.persistence;


import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryPostgres implements IUserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(User user) {
        this.entityManager.persist(UserEntity.fromDomain(user));

    }
}
