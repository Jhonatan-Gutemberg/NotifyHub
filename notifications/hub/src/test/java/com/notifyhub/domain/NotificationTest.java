package com.notifyhub.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationTest {

    @Test
    @DisplayName("Should create a notification successfully when data is valid")
    void shouldCreateNotificationWithValidData() {
        // Arrange
        Recipient recipient = new Recipient("test@email.com");
        NotificationMessage message = new NotificationMessage("Subject", "Content body");
        NotificationType type = NotificationType.EMAIL;
        Priority priority = Priority.HIGH;

        // Act
        Notification notification = new Notification(recipient, message, type, priority);

        // Assert
        assertNotNull(notification.getId(), "ID should not be null");
        assertNotNull(notification.getCreatedAt(), "Creation date should not be null");
        assertEquals(recipient, notification.getRecipient());
        assertEquals(message, notification.getMessage());
        assertEquals(type, notification.getType());
        assertEquals(priority, notification.getPriority());
    }

    @Test
    @DisplayName("Should throw NullPointerException if recipient is null")
    void shouldThrowExceptionWhenRecipientIsNull() {
        // Arrange
        NotificationMessage message = new NotificationMessage("Title", "Message content");

        // Act & Assert
        var exception = assertThrows(NullPointerException.class, () -> {
            new Notification(null, message, NotificationType.SMS, Priority.LOW);
        });

        assertNotNull(exception);
    }

    @Test
    @DisplayName("Should fail when attempting to create notification without recipient")
    void shouldFailWhenRecipientIsMissing() {
        // Arrange
        NotificationMessage message = new NotificationMessage("Title", "Message content");
        
        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> new Notification(null, message, NotificationType.SMS, Priority.LOW));
    }
}