package com.notifyhub.application.port;

import com.notifyhub.domain.NotificationType;

public interface INotificationStrategyFactory {
    INotificationStrategy get(NotificationType type);
}

