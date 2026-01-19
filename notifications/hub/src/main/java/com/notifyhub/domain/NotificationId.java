package com.notifyhub.domain;

import java.time.LocalDate;
import java.util.Objects;

public class NotificationId {

    private final Long value;
    private final LocalDate date;

    private NotificationId(Long value, LocalDate date) {
        this.value = Objects.requireNonNull(value);
        this.date = Objects.requireNonNull(date);
    }

    public static NotificationId of(Long value, LocalDate date) {
        return new NotificationId(value, date);
    }

    @Override
    public String toString() {
        return value + "-" + date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NotificationId)) return false;
        NotificationId that = (NotificationId) obj;
        return value.equals(that.value) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }
}
