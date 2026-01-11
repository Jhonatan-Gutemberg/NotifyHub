package com.notifyhub.application.decorator;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public class RetryDecorator extends NotificationDecorator {

    private final int maxAttempts;

    public RetryDecorator(INotificationStrategy wrapped, int maxAttempts) {
        super(wrapped);
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void send(Recipient recipient, Messages message) {
        int attempt = 0;

        while (true) {
            try {
                attempt++;
                wrapped.send(recipient, message);
                return;
            } catch (Exception e) {
                if (attempt >= maxAttempts) {
                    throw e;
                }
            }
        }
    }
}

