package com.notifyhub.application.builder;

import com.notifyhub.application.exception.NotificationValidationException;
import com.notifyhub.domain.NotificationMessage;

public class NotificationMessageBuilder {
    private String title;
    private String content;

    public NotificationMessageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NotificationMessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public NotificationMessage build() {
        if (title == null || title.isEmpty()) {
            throw new NotificationValidationException("Title is required");
        }
        if (content == null || content.isEmpty()) {
           throw new NotificationValidationException("Content is required");
        }
        return new NotificationMessage(title, content);
    }

}
