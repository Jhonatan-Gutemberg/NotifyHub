package com.notifyhub.application.port;

import com.notifyhub.domain.Notification;

public interface INotificationObserver {
    void onNotificationSent(Notification notification);

    void onNotificationFailed(Notification notification, String error);}
