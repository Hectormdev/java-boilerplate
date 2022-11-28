package com.acidtango.boilerplate.shared.infrastructure.configuration;


import com.acidtango.boilerplate.shared.domain.IClockService;
import com.acidtango.boilerplate.shared.domain.IUUIDService;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceVanilla;
import com.acidtango.boilerplate.shared.infrastructure.uuid.UUIDServiceVanilla;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedInjectionConfiguration {
    @Bean
    public static IClockService clockService() {
        return new ClockServiceVanilla();
    }

    @Bean
    public static IUUIDService uuidService() {
        return new UUIDServiceVanilla();
    }
}
