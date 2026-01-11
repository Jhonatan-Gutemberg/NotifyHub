package com.notifyhub.application.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public class LoggingDecorator extends NotificationDecorator {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingDecorator.class);

    public LoggingDecorator(INotificationStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public void send(Recipient recipient, Messages message) {
        logger.info(
            "Sending notification to {} with title {}",
            recipient.getAddress(),
            message.gettitle()
        );

        super.send(recipient, message);

        logger.info(
            "Notification successfully sent to {}",
            recipient.getAddress()
        );
    }
}

