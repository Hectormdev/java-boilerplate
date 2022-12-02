package com.acidtango.boilerplate.shared.infrastructure.configuration;


import com.acidtango.boilerplate.shared.domain.ClockService;
import com.acidtango.boilerplate.shared.domain.IDService;
import com.acidtango.boilerplate.shared.infrastructure.clock.ClockServiceVanilla;
import com.acidtango.boilerplate.shared.infrastructure.uuid.IDServiceVanilla;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedInjectionConfiguration {
    @Bean
    public static ClockService clockService() {
        return new ClockServiceVanilla();
    }

    @Bean
    public static IDService uuidService() {
        return new IDServiceVanilla();
    }
}
