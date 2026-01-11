package com.notifyhub.application.decorator;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public abstract class NotificationDecorator implements INotificationStrategy {
    protected final INotificationStrategy wrapped;

    public NotificationDecorator(INotificationStrategy wrappee) {
        this.wrapped = wrappee;
    }

    @Override
    public void send(Recipient recipient, Messages message) {
        wrapped.send(recipient, message);
    }

}
