package com.acidtango.boilerplate.users.infrastructure.configuration;

import com.acidtango.boilerplate.users.domain.UserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.UserRepositoryJPA;
import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.UserRepositoryPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInjectionConfiguration {

    @Bean
    UserRepository userRepository(UserRepositoryJPA repository) {
        return new UserRepositoryPostgres(repository);
    }


}
