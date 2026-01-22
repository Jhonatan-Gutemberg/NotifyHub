package com.notifyhub.application.config;

import com.notifyhub.application.decorator.LoggingDecorator;
import com.notifyhub.application.exception.ConfigurationException;
import com.notifyhub.application.factory.NotificationFactory;
import com.notifyhub.application.observer.DatabaseLogObserver;
import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.domain.NotificationType;
import com.notifyhub.infra.EmailNotificationStrategy;
import com.notifyhub.infra.db.H2DatabaseInitializer;
import com.notifyhub.infra.db.H2Server;
import com.notifyhub.infra.repository.H2NotificationLogRepository;

import io.github.cdimascio.dotenv.Dotenv;

public class NotificationAplication {
    public static NotificationUseCase start() {
        Dotenv dotenv = Dotenv.configure()
        .filename(".env") 
        .load();
        String user = dotenv.get("EMAIL_USER");
        String pass = dotenv.get("EMAIL_PASS");
        if (user == null || pass == null) {
            throw new ConfigurationException(
                    "Erro: Variáveis EMAIL_USER ou EMAIL_PASS não encontradas no arquivo .env");
        }
        try {
            H2DatabaseInitializer.init();
            H2Server.start();
            NotificationFactory factory = new NotificationFactory();
            INotificationStrategy email = new LoggingDecorator(
                    new EmailNotificationStrategy(user, pass));
            factory.registerStrategy(NotificationType.EMAIL, email);
            NotificationUseCase useCase = new NotificationUseCase(factory);
            useCase.addObserver(new DatabaseLogObserver(new H2NotificationLogRepository()));
            return useCase;
        } catch (Exception e) {
            throw new ConfigurationException("Erro ao iniciar a aplicação", e);
        }
    }
}
