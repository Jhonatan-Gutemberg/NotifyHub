package com.notifyhub.infraadapter.controller;

import com.notifyhub.application.dto.NotificationRequestDTO;
import com.notifyhub.application.usecase.NotificationUseCase;
import com.notifyhub.domain.Notification;

public class NotificationController {

    private final NotificationUseCase useCase;

    public NotificationController(NotificationUseCase notificationUseCase) {
        this.useCase = notificationUseCase;
    }

    public Notification sendNotification(NotificationRequestDTO notificationDto) {
        return useCase.convertDTO(notificationDto);
    }

}
