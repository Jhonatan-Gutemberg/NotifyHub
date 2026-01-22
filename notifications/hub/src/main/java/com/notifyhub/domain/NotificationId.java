package com.notifyhub.domain;

import java.util.Objects;
import java.util.UUID;

public final class NotificationId {

    private final String value;

    private NotificationId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static NotificationId generate() {
        return new NotificationId(UUID.randomUUID().toString());
    }

    public static NotificationId of(String value) {
        return new NotificationId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationId)) return false;
        NotificationId that = (NotificationId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
