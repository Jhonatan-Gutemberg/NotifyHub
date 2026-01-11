package com.notifyhub.application.usecase;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.notifyhub.application.port.INotificationObserver;
import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public class NotificationUseCase {
    private final List<INotificationObserver> observers = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(NotificationUseCase.class);
    private INotificationStrategy notificationStrategy;

    public NotificationUseCase(INotificationStrategy strategy) {
        this.notificationStrategy = strategy;
    }

    public void addObserver(INotificationObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(INotificationObserver obs) {
        observers.remove(obs);
    }

    public void sendNotification(Recipient recipient, Messages message) {
        validate(recipient, message);
        try {
            send(recipient, message);
            observers.forEach(obs -> obs.onNotificationSent(recipient, message));
            log(message, recipient);
        } catch (Exception e) {
            observers.forEach(obs -> obs.onNotificationFailed(recipient, message, e.getMessage()));
            logger.error("Failed to send notification to recipient: {} with message: {}. Error: {}",
                    recipient.getAddress(), message.gettitle(), e.getMessage());
        }
    }

    private void validate(Recipient recipient, Messages message) {
        if (recipient == null) {
            logger.error("Recipient is null");
            throw new IllegalArgumentException("Recipient is required");
        }
        if (message == null) {
            logger.error("Message is null");
            throw new IllegalArgumentException("Message is required");
        }
    }

    private void send(Recipient recipient, Messages message) {
        notificationStrategy.send(recipient, message);
    }

    private void log(Messages message, Recipient recipient) {
        logger.info("Sending notification to recipient: {} with message: {}", recipient.getAddress(),
                message.gettitle());
    }
}
