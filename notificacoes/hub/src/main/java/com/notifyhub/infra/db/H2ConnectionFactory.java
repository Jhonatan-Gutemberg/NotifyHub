package com.notifyhub.infra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.notifyhub.application.exception.ConfigurationException;

public class H2ConnectionFactory {

    private static final String URL =
            "jdbc:h2:file:./data/notifyhub-db";

    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConfigurationException("Driver H2 não encontrado no classpath.",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
