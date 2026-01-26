package com.notifyhub.application.observer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import com.notifyhub.application.port.INotificationLogRepository;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.NotificationType;
import com.notifyhub.domain.Priority;
import com.notifyhub.domain.Recipient;

@ExtendWith(MockitoExtension.class)
class DatabaseLogObserverTest {

    @Mock
    private INotificationLogRepository repository;

    @InjectMocks
    private DatabaseLogObserver observer;

    @Test
    @DisplayName("Should mask recipient email and save success log")
    void shouldMaskEmailAndSaveSuccess() {
        // Arrange
        Recipient recipient = new Recipient("thiago@gmail.com", "Thiago");
        Notification notification = new Notification(
                recipient,
                new NotificationMessage("Subject", "Body"),
                NotificationType.EMAIL,
                Priority.HIGH);

        // Act
        observer.onNotificationSent(notification);

        // Assert
        ArgumentCaptor<Notification> logCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(repository).saveSuccess(logCaptor.capture());

        Notification capturedLog = logCaptor.getValue();

        assertEquals("th************@gm***il.com", capturedLog.getRecipient().getAddress());
    }

    @Test
    @DisplayName("Should save failure log when notification fails")
    void shouldSaveFailureLog() {
        // Arrange
        Recipient recipient = new Recipient("contact@company.com", "Company");
        Notification notification = new Notification(
                recipient, 
                mock(NotificationMessage.class), 
                NotificationType.SMS,
                Priority.LOW);
        String errorMessage = "Provider offline";

        // Act
        observer.onNotificationFailed(notification, errorMessage);

        // Assert
        verify(repository, times(1)).saveFailure(any(Notification.class), eq(errorMessage));
    }

    @Test
    @DisplayName("Should not throw exception if the repository fails (internal try-catch)")
    void shouldNotThrowExceptionWhenRepositoryFails() {
        // Arrange
        doThrow(new RuntimeException("DB Error")).when(repository).saveSuccess(any());
        Notification notification = new Notification(
                new Recipient("a@b.com", "A"), 
                mock(NotificationMessage.class), 
                NotificationType.EMAIL, 
                Priority.LOW);

        // Act & Assert
        assertDoesNotThrow(() -> observer.onNotificationSent(notification));
    }
}