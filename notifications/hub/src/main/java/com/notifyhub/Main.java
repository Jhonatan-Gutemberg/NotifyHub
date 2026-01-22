package com.notifyhub;

import java.io.IOException;

import com.notifyhub.application.config.NotificationAplication;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.infra.http.HttpServerConfig;
import com.notifyhub.infraadapter.controller.NotificationController;

public class Main {
    public static void main(String[] args) throws IOException {
        NotificationUseCase useCase = NotificationAplication.start();
        NotificationController controller = new NotificationController(useCase);
        HttpServerConfig.start(controller);
    }
}