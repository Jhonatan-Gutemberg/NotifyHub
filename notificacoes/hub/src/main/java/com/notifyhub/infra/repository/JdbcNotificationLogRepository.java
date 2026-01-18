package com.notifyhub.infra.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.notifyhub.application.port.INotificationLogRepository;
import com.notifyhub.domain.Notification;
import com.notifyhub.infra.persistence.NotificationLog;

public class JdbcNotificationLogRepository
        implements INotificationLogRepository {

    private static final String URL = "jdbc:h2:file:./data/notifyhub-db";

    private static final String USER = "sa";
    private static final String PASS = "";

    @Override
    public void saveSuccess(Notification notification) {
        save(buildLog(notification, "SUCCESS", null));
    }

    @Override
    public void saveFailure(Notification notification, String error) {
        save(buildLog(notification, "FAILED", error));
    }

    private void save(NotificationLog log) {
        String sql = """
                    INSERT INTO notification_log
                    (notification_id, recipient, title, content, status, error_message, created_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, log.getNotificationId());
            ps.setString(2, log.getRecipient());
            ps.setString(3, log.getTitle());
            ps.setString(4, log.getContent());
            ps.setString(5, log.getStatus());
            ps.setString(6, log.getErrorMessage());
            ps.setTimestamp(7, java.sql.Timestamp.from(log.getCreatedAt()));

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to persist notification log", e);
        }
    }

    private NotificationLog buildLog(
            Notification n,
            String status,
            String error) {
        return new NotificationLog(
                n.getId().toString(),
                n.getRecipient().getAddress(),
                n.getMessage().getTitle(),
                n.getMessage().getContent(),
                status,
                error);
    }
}
