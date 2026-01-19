package com.notifyhub.infra.db;

import org.h2.tools.Server;

import com.notifyhub.application.exception.ConfigurationException;

public class H2Server {

    public static void start() {
        try {
            Server.createTcpServer(
                    "-tcp",
                    "-tcpAllowOthers",
                    "-tcpPort", "9092"
            ).start();

            Server.createWebServer(
                    "-web",
                    "-webAllowOthers",
                    "-webPort", "8082"
            ).start();

            System.out.println("H2 Server iniciado");
        } catch (Exception e) {
            throw new ConfigurationException("Failed to start H2 Server", e);
        }
    }
}
