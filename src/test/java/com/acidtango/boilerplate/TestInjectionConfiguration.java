package com.acidtango.boilerplate;

import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceFake;
import com.acidtango.boilerplate.shared.infrastructure.uuid.UUIDServiceFake;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.UserRepositoryMemory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestInjectionConfiguration {
    @Primary
    @Bean
    @ConditionalOnProperty("test.repository.memory")
    IUserRepository userFakeRepository() {
        return new UserRepositoryMemory();
    }

    @Primary
    @Bean
    @ConditionalOnProperty("test.clockService")
    public static IClockService clockFakeService() {
        return new ClockServiceFake();
    }

    @Primary
    @Bean
    @ConditionalOnProperty("test.uuidService")
    public static IUUIDService uuidFakeService() {
        return new UUIDServiceFake();
    }
}

