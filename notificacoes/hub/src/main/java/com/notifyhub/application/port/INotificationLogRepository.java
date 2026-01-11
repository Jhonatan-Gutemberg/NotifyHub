package com.notifyhub.application.port;

import com.notifyhub.domain.Notification;

public interface INotificationLogRepository {

    void saveSuccess(Notification notification);

    void saveFailure(Notification notification, String error);

}
