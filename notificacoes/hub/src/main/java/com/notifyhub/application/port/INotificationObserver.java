package com.notifyhub.application.port;

import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public interface INotificationObserver {
    void onNotificationSent(Recipient recipient, Messages message);

    void onNotificationFailed(Recipient recipient, Messages message, String error);}
