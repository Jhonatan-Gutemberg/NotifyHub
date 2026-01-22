package com.notifyhub.infra.http;

import java.io.IOException;
import java.io.OutputStream;

import com.notifyhub.application.dto.NotificationRequestDTO;
import com.notifyhub.application.mapper.JsonMapper;
import com.notifyhub.infraadapter.controller.NotificationController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class NotificationHttpHandler implements HttpHandler {

    private final NotificationController controller;

    public NotificationHttpHandler(NotificationController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        String body = new String(exchange.getRequestBody().readAllBytes());
        NotificationRequestDTO request = JsonMapper.fromJson(
                body,
                NotificationRequestDTO.class);
        var response = controller.sendNotification(request);
        byte[] responseBytes = ("Notification created with ID: " + response.getId()).getBytes();

        exchange.sendResponseHeaders(201, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}
