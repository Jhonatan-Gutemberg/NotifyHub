package com.notifyhub.application.dto;


public record NotificationRequestDTO(
    String email,
    String name,
     String type,
     String priority
) {
    
}
