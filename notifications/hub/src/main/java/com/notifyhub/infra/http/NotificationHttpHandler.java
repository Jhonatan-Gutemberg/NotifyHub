package com.notifyhub.infra.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.notifyhub.application.dto.NotificationRequestDTO;
import com.notifyhub.application.mapper.JsonMapper;
import com.notifyhub.infraadapter.controller.NotificationController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import io.github.cdimascio.dotenv.Dotenv;

public class NotificationHttpHandler implements HttpHandler {

    private final NotificationController controller;
    Dotenv dotenv = Dotenv.configure()
                .filename(".env")
                .load();

    public NotificationHttpHandler(NotificationController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        setupCorsHeaders(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            try (exchange) {
                exchange.sendResponseHeaders(204, -1);
            }
            return;
        }

        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendTextResponse(exchange, "Method Not Allowed", 405);
            return;
        }

        try (exchange) {
            byte[] requestBytes = exchange.getRequestBody().readAllBytes();
            String body = new String(requestBytes, StandardCharsets.UTF_8);

            NotificationRequestDTO request = JsonMapper.fromJson(body, NotificationRequestDTO.class);
            var response = controller.sendNotification(request);

            String jsonResponse = String.format("{\"message\": \"Notification created\", \"id\": \"%s\"}", response.getId());
            sendJsonResponse(exchange, jsonResponse, 201);

        } catch (Exception e) {
            sendTextResponse(exchange, "Internal Server Error: " + e.getMessage(), 500);
        }
    }

    private void setupCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", dotenv.get("CORS_ORIGIN"));
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    private void sendJsonResponse(HttpExchange exchange, String json, int status) throws IOException {
        byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private void sendTextResponse(HttpExchange exchange, String text, int status) throws IOException {
        byte[] responseBytes = text.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}