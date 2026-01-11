package com.notifyhub.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notifyhub.application.port.INotificationObserver;
import com.notifyhub.application.port.INotificationLogRepository;
import com.notifyhub.domain.Notification;

public class DatabaseLogObserver implements INotificationObserver {

    private static final Logger logger =
            LoggerFactory.getLogger(DatabaseLogObserver.class);

    private final INotificationLogRepository repository;

    public DatabaseLogObserver(INotificationLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onNotificationSent(Notification notification) {
        try {
            repository.saveSuccess(notification);
            logger.info(
                "Notification {} persisted as SUCCESS",
                notification.getId()
            );
        } catch (Exception e) {
            logger.error(
                "Failed to persist SUCCESS notification {}",
                notification.getId(),
                e
            );
        }
    }

    @Override
    public void onNotificationFailed(Notification notification, String error) {
        try {
            repository.saveFailure(notification, error);
            logger.error(
                "Notification {} persisted as FAILED",
                notification.getId()
            );
        } catch (Exception e) {
            logger.error(
                "Failed to persist FAILED notification {}",
                notification.getId(),
                e
            );
        }
    }
}
