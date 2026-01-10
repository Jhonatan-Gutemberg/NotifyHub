package notificacoes.domain;

import java.time.Instant;
import java.util.Objects;

public class Notification {
    private final NotificationId id;
    private Recipient recipient;
    private Message message;
    private NotificationType type;
    private Priority priority;
    private final Instant createdAt;

    Notification(Recipient recipient, Message message, NotificationType type, Priority priority) {
        this.id = NotificationIdFactory.generate();
        this.recipient = Objects.requireNonNull(recipient);
        this.message = Objects.requireNonNull(message);
        this.type = Objects.requireNonNull(type);
        this.priority = Objects.requireNonNull(priority);
        this.createdAt = Instant.now();
    }

    public NotificationId getId() {
        return id;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public Message getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
