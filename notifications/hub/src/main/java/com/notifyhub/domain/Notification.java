package com.notifyhub.domain;

import java.time.Instant;
import java.util.Objects;

public class Notification {
    private final NotificationId id;
    private Recipient recipient;
    private final NotificationMessage message;
    private final NotificationType type;
    private final Priority priority;
    private final Instant createdAt;

    public Notification(Recipient recipient, NotificationMessage message, NotificationType type, Priority priority) {
        this.id = NotificationId.generate();
        this.recipient = Objects.requireNonNull(recipient);
        this.message = Objects.requireNonNull(message);
        this.type = Objects.requireNonNull(type);
        this.priority = Objects.requireNonNull(priority);
        this.createdAt = Instant.now();
    }

    public NotificationId getId() {
        return id;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public NotificationMessage getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
