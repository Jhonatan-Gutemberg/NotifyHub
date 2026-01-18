package com.notifyhub.application.usecase;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notifyhub.application.port.INotificationObserver;
import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.port.NotificationStrategyFactory;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.Recipient;

public class NotificationUseCase {
    private final List<INotificationObserver> observers = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(NotificationUseCase.class);
    private final NotificationStrategyFactory strategyFactory;  
    private final INotificationStrategy notificationStrategy;

    public NotificationUseCase(INotificationStrategy strategy) {
        this.notificationStrategy = strategy;
        this.strategyFactory = null;
    }

    public void addObserver(INotificationObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(INotificationObserver obs) {
        observers.remove(obs);
    }

    public void sendNotification(Notification notification) {

        validate(notification);
        Recipient recipient = notification.getRecipient();
        try {
            send(notification);
            observers.forEach(obs -> obs.onNotificationSent(notification));
            log(notification);
        } catch (Exception e) {
            observers.forEach(obs -> {
                try {
                    strategyFactory.get(notification.getType()).send(notification);
                    obs.onNotificationFailed(notification, e.getMessage());
                } catch (Exception ex) {
                    logger.warn("Observer failure callback failed: {}", ex.getMessage());
                }
            });

            logger.error("Failed to send notification to {}. Error: {}",
                    recipient.getAddress(), e.getMessage());
        }
    }

    private void validate(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification is null");
        }
        if (notification.getRecipient() == null) {
            logger.error("Recipient is null");
            throw new IllegalArgumentException("Recipient is required");
        }
        if (notification.getMessage() == null) {
            logger.error("Message is null");
            throw new IllegalArgumentException("Message is required");
        }
        if (notification.getType() == null) {
            logger.error("Type is null");
            throw new IllegalArgumentException("Type is required");
        }
        if (notification.getPriority() == null) {
            logger.error("Priority is null");
            throw new IllegalArgumentException("Priority is required");
        }
    }

    private void send(Notification notification) {
        notificationStrategy.send(notification);
    }

    private void log(Notification notification) {
        Recipient recipient = notification.getRecipient();
        NotificationMessage message = notification.getMessage();
        logger.info("Sending notification to recipient: {} with message: {}", recipient.getAddress(),
                message.getTitle());
    }
}
