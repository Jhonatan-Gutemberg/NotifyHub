package com.notifyhub.application.decorator;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Notification;

public abstract class NotificationDecorator implements INotificationStrategy {
    protected final INotificationStrategy wrapped;

    public NotificationDecorator(INotificationStrategy wrappee) {
        this.wrapped = wrappee;
    }

    @Override
    public void send(Notification notification) {
        wrapped.send(notification);
    }

}
