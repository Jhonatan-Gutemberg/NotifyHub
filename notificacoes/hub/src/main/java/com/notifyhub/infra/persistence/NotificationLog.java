package com.notifyhub.infra.persistence;

import java.time.Instant;

public class NotificationLog {

    private final String notificationId;
    private final String recipient;
    private final String title;
    private final String status;
    private final String errorMessage;
    private final Instant createdAt;

    public NotificationLog(
            String notificationId,
            String recipient,
            String title,
            String status,
            String errorMessage) {
        this.notificationId = notificationId;
        this.recipient = recipient;
        this.title = title;
        this.status = status;
        this.errorMessage = errorMessage;
        this.createdAt = Instant.now();
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
