package com.acidtango.boilerplate.shared.infrastructure.clock;

import com.acidtango.boilerplate.shared.domain.IClockService;

import java.time.LocalDateTime;

public class ClockServiceVanilla implements IClockService {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
