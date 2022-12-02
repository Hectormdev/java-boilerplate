package com.acidtango.boilerplate;

import com.acidtango.boilerplate.shared.domain.ClockService;
import com.acidtango.boilerplate.shared.domain.IDService;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceFake;
import com.acidtango.boilerplate.shared.infrastructure.uuid.IDServiceFake;
import com.acidtango.boilerplate.users.domain.UserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.UserRepositoryMemory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestInjectionConfiguration {
    @Primary
    @Bean
    @ConditionalOnProperty("test.clockService")
    public static ClockService clockFakeService() {
        return new ClockServiceFake();
    }

    @Primary
    @Bean
    @ConditionalOnProperty("test.uuidService")
    public static IDService uuidFakeService() {
        return new IDServiceFake();
    }

    @Primary
    @Bean
    @ConditionalOnProperty("test.repository.memory")
    UserRepository userFakeRepository() {
        return new UserRepositoryMemory();
    }
}

