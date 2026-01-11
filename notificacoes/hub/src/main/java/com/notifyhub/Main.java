package com.notifyhub;

import com.notifyhub.application.builder.HtmlEmailBuilder;
import com.notifyhub.application.builder.MessagesBuilder;
import com.notifyhub.application.builder.NotificationBuilder;
import com.notifyhub.application.decorator.LoggingDecorator;
import com.notifyhub.application.port.INotificationObserver;
import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.NotificationType;
import com.notifyhub.domain.Priority;
import com.notifyhub.domain.Recipient;
import com.notifyhub.infra.DatabaseLogObserver;
import com.notifyhub.infra.EmailNotification;
import com.notifyhub.infra.db.H2DatabaseInitializer;
import com.notifyhub.infra.db.H2Server;
import com.notifyhub.infra.repository.H2NotificationLogRepository;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("EMAIL_USER");
        String pass = dotenv.get("EMAIL_PASS");

        if (user == null || pass == null) {
            throw new RuntimeException("Erro: Variáveis EMAIL_USER ou EMAIL_PASS não encontradas no arquivo .env");
        }

        H2Server.start();
        H2DatabaseInitializer.init();
        INotificationStrategy email = new LoggingDecorator(
                new EmailNotification(user, pass));

        NotificationUseCase service = new NotificationUseCase(email);
        INotificationObserver dbObserver = new DatabaseLogObserver(new H2NotificationLogRepository());
        service.addObserver(dbObserver);
        Recipient recipient = new Recipient("jhogufer@hotmail.com", "Jhonatan");
        String title = "Notificação com strategy com E-mail padronizado - Design Patterns";
        String html = new HtmlEmailBuilder().setRecipient(recipient.getName()).build();
        Messages message = new MessagesBuilder()
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