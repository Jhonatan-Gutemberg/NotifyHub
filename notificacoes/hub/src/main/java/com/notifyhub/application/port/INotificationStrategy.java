package com.notifyhub.application.port;

import com.notifyhub.domain.Notification;

public interface INotificationStrategy {
    void send(Notification notification);
    
}
