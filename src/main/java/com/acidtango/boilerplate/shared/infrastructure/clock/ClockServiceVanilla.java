package com.acidtango.boilerplate.shared.infrastructure.clock;

import com.acidtango.boilerplate.shared.domain.IClockService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ClockServiceVanilla implements IClockService {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @Override
    public long getTimeStamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
