package com.notifyhub.application.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.Recipient;

public class LoggingDecorator extends NotificationDecorator {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingDecorator.class);

    public LoggingDecorator(INotificationStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public void send(Notification notification) {
        Recipient recipient = notification.getRecipient();
        NotificationMessage message = notification.getMessage();
        logger.info(
            "Sending notification to {} with title {}",
            recipient.getAddress(),
            message.getTitle()
        );

        super.send(notification);

        logger.info(
            "Notification successfully sent to {}",
            recipient.getAddress()
        );
    }
}

