package com.notifyhub.application.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notifyhub.application.port.INotificationLogRepository;
import com.notifyhub.application.port.INotificationObserver;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.Recipient;

public class DatabaseLogObserver implements INotificationObserver {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLogObserver.class);

    private final INotificationLogRepository repository;

    public DatabaseLogObserver(INotificationLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onNotificationSent(Notification notification) {
        try {
            Notification log = createLog(notification);
            repository.saveSuccess(log);
            logger.info(
                    "Notification {} persisted as SUCCESS",
                    notification.getId());
        } catch (Exception e) {
            logger.error(
                    "Failed to persist SUCCESS notification {}",
                    notification.getId(),
                    e);
        }
    }

    @Override
    public void onNotificationFailed(Notification notification, String error) {
        try {
            Notification log = createLog(notification);
            repository.saveFailure(log, error);
            logger.error(
                    "Notification {} persisted as FAILED",
                    notification.getId());
        } catch (Exception e) {
            logger.error(
                    "Failed to persist FAILED notification {}",
                    notification.getId(),
                    e);
        }
    }

    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        return email.replaceAll("(?<=.{2}).(?=[^@]*?@)|(?<=@.{2}).(?=.*\\.)", "***");

    }

    private Notification createLog(Notification notificationLog) {

        String maskAddress = maskEmail(notificationLog.getRecipient().getAddress());
        Recipient recipient = new Recipient(maskAddress, notificationLog.getRecipient().getName());
        Notification log = new Notification(recipient, notificationLog.getMessage(), notificationLog.getType(),
                notificationLog.getPriority());
        return log;
    }
}
