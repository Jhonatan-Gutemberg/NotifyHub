package com.notifyhub.application.fatory;

import java.util.Map;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.port.NotificationStrategyFactory;
import com.notifyhub.domain.NotificationType;

public class NotificationFactory
        implements NotificationStrategyFactory {

    private final Map<NotificationType, INotificationStrategy> strategies;

    public NotificationFactory(Map<NotificationType, INotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public INotificationStrategy get(NotificationType type) {
        return strategies.get(type);
    }
}

