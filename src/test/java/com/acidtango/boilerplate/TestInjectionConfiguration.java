package com.acidtango.boilerplate;

import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceFake;
import com.acidtango.boilerplate.shared.infrastructure.uuid.UUIDServiceFake;
import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.infrastructure.persistence.UserRepositoryMemory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestInjectionConfiguration {
    @Primary
    @Bean
    IUserRepository userFakeRepository() {
        return new UserRepositoryMemory();
    }

    @Primary
    @Bean
    public static IClockService clockFakeService() {
        return new ClockServiceFake();
    }

    @Primary
    @Bean
    public static IUUIDService uuidFakeService() {
        return new UUIDServiceFake();
    }
}

