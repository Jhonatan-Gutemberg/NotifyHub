package com.notifyhub.application.fatory;

import java.util.HashMap;
import java.util.Map;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.port.INotificationStrategyFactory;
import com.notifyhub.domain.NotificationType;

public class NotificationFactory
        implements INotificationStrategyFactory {

    private final Map<NotificationType, INotificationStrategy> strategies = new HashMap<>();

    public void registerStrategy(NotificationType type, INotificationStrategy strategy) {
        strategies.put(type, strategy);
    }

    @Override
    public INotificationStrategy get(NotificationType type) {
        INotificationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Nenhuma estratégia encontrada para o tipo: " + type);
        }
        return strategy;
    }
}
