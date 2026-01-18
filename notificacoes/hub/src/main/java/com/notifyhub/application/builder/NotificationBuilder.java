package com.notifyhub.application.builder;

import com.notifyhub.domain.Notification;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.NotificationType;
import com.notifyhub.domain.Priority;
import com.notifyhub.domain.Recipient;

public class NotificationBuilder {
    private Recipient recipient;
    private NotificationMessage message;
    private NotificationType type;
    private Priority priority;

    public NotificationBuilder setRecipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public NotificationBuilder setMessage(NotificationMessage message) {
        this.message = message;
        return this;
    }

    public NotificationBuilder setType(NotificationType type) {
        this.type = type;
        return this;
    }

    public NotificationBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public Notification build() {
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient is required");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("Notification type is required");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority is required");
        }
        return new Notification(recipient, message, type, priority);
    }
    
}
