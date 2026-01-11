package com.notifyhub;

import com.notifyhub.application.builder.HtmlEmailBuilder;
import com.notifyhub.application.builder.MessagesBuilder;
import com.notifyhub.application.decorator.LoggingDecorator;
import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;
import com.notifyhub.infra.EmailNotification;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        INotificationStrategy email = new LoggingDecorator(
                new EmailNotification(dotenv.get("EMAIL_USER"), dotenv.get("EMAIL_PASS")));
        NotificationUseCase service = new NotificationUseCase(email);
        Recipient recipient = new Recipient("teste@oute.com", "Teste");
        String title = "Notificação com strategy com E-mail padronizado - Design Patterns";
        String html = new HtmlEmailBuilder().setRecipient(recipient.getName()).build();
        Messages message = new MessagesBuilder()
                .setTitle(title)
                .setContent(html)
                .build();
        service.sendNotification(recipient, message);
    }
}