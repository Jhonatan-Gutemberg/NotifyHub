package com.notifyhub.application.decorator;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Notification;

public class RetryDecorator extends NotificationDecorator {

    private final int maxAttempts;

    public RetryDecorator(INotificationStrategy wrapped, int maxAttempts) {
        super(wrapped);
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void send(Notification notification) {
        int attempt = 0;

        while (true) {
            try {
                attempt++;
                wrapped.send(notification);
                return;
            } catch (Exception e) {
                if (attempt >= maxAttempts) {
                    throw e;
                }
            }
        }
    }
}

