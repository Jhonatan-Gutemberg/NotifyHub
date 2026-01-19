package com.notifyhub;

import com.notifyhub.application.builder.NotificationBuilder;
import com.notifyhub.application.builder.NotificationMessageBuilder;
import com.notifyhub.application.config.NotificationAplication;
import com.notifyhub.application.templates.DesignPatternsEmailTemplate;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.NotificationType;
import com.notifyhub.domain.Priority;
import com.notifyhub.domain.Recipient;

public class Main {
    public static void main(String[] args) {

        NotificationUseCase service = NotificationAplication.start();
        String emailSender = "xxxxxxx";
        String recipientName = "xxxxxxx";
        Recipient recipient = new Recipient(emailSender,recipientName);
        String title = "Notificação com strategy com E-mail padronizado - Design Patterns";
        String html = new DesignPatternsEmailTemplate().recipient(recipientName).build();
        NotificationMessage message = new NotificationMessageBuilder()
                .setTitle(title)
                .setContent(html)
                .build();
        Notification notification = new NotificationBuilder()
                .setRecipient(recipient)
                .setMessage(message)
                .setType(NotificationType.EMAIL)
                .setPriority(Priority.LOW)
                .build();
        service.sendNotification(notification);
    }
}