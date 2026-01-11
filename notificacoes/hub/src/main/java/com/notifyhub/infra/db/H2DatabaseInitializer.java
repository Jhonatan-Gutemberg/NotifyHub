package com.notifyhub.infra.db;

import java.sql.Connection;
import java.sql.Statement;

public class H2DatabaseInitializer {

    public static void init() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS notification_log (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        notification_id VARCHAR(36) NOT NULL,
                        recipient VARCHAR(255),
                        type VARCHAR(50),
                        priority VARCHAR(50),
                        title VARCHAR(255),
                        status VARCHAR(20),
                        error_message VARCHAR(500),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    );
                """;

        try (Connection conn = H2ConnectionFactory.getConnection();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
}
