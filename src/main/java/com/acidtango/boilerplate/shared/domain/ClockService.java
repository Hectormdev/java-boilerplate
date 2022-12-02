package com.acidtango.boilerplate.shared.domain;

import java.time.LocalDateTime;

public interface ClockService {
    LocalDateTime getTime();

    long getTimeStamp();
}
