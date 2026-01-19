package com.notifyhub.application.exception;

public class NotificationSendException extends RuntimeException {
    public NotificationSendException(String message, Throwable cause) {
        super(message, cause);
    }
}