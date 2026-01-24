package com.notifyhub.infra.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.notifyhub.infraadapter.controller.NotificationController;
import com.sun.net.httpserver.HttpServer;

import io.github.cdimascio.dotenv.Dotenv;

public class HttpServerConfig {

    public static void start(NotificationController controller) throws IOException {

        Dotenv dotenv = Dotenv.configure()
                .filename(".env")
                .load();

        int port = Integer.parseInt(
                dotenv.get("HTTP_PORT", "8080")
        );
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext(
            "/notifications",
            new NotificationHttpHandler(controller)
        );

        server.start();
    }
}
