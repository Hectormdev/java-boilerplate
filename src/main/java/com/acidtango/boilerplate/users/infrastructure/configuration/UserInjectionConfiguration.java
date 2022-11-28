package com.acidtango.boilerplate.users.infrastructure.configuration;

import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.UserRepositoryPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInjectionConfiguration {

    @Bean
    IUserRepository userRepository(){
        return new UserRepositoryPostgres();
    };


}
