package com.notifyhub.infra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.notifyhub.application.exception.ConfigurationException;
import com.notifyhub.application.port.INotificationLogRepository;
import com.notifyhub.domain.Notification;
import com.notifyhub.infra.db.H2ConnectionFactory;

public class H2NotificationLogRepository
        implements INotificationLogRepository {

    @Override
    public void saveSuccess(Notification notification) {
        save(notification, "SUCCESS", null);
    }

    @Override
    public void saveFailure(Notification notification, String error) {
        save(notification, "FAILED", error);
    }

    private void save(
            Notification notification,
            String status,
            String error) {

        String sql = """
                    INSERT INTO notification_log
                    (notification_id, recipient, type, priority, title, content, status, error_message, created_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = H2ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, notification.getId().toString());
            ps.setString(2, notification.getRecipient().getAddress());
            ps.setString(3, notification.getType().name());
            ps.setString(4, notification.getPriority().name());
            ps.setString(5, notification.getMessage().getTitle());
            ps.setString(6,notification.getMessage().getContent());
            ps.setString(7, status);
            ps.setString(8, error);
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();

        } catch (Exception e) {
            throw new ConfigurationException("Failed to persist notification log", e);
        }
    }
}
