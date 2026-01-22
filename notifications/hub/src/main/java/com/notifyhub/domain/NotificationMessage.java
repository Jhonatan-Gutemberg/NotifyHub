package com.notifyhub.domain;

import java.util.UUID;

public class NotificationMessage {
    private final String id;
    private final String title;
    private final String content;
    

    public NotificationMessage(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = validate(title, "Title");
        this.content = validate(content, "Content");
    }

    public NotificationMessage(String id, String title, String content) {
        this.id = id;
        this.title = validate(title, "title");
        this.content = validate(content, "content");
    }

    private String validate(String value, String filed) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(filed + " is required");
        }
        return value;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
