package com.notifyhub.application.port;

import com.notifyhub.domain.NotificationType;

public interface NotificationStrategyFactory {
    INotificationStrategy get(NotificationType type);
}

