package com.acidtango.boilerplate.users.infrastructure.persistence.userRepository;

import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, String> { }
