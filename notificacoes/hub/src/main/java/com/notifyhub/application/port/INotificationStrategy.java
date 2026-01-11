package com.notifyhub.application.port;

import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public interface INotificationStrategy {
    void send(Recipient recipient, Messages message);
    
}
