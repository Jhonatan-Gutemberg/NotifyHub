package com.notifyhub.domain;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public final class NotificationIdFactory {

    private static final AtomicLong SEQUENCE = new AtomicLong(1);

    private NotificationIdFactory() {}

    public static NotificationId generate() {
        return NotificationId.of(
            SEQUENCE.getAndIncrement(),
            LocalDate.now()
        );
    }
}
