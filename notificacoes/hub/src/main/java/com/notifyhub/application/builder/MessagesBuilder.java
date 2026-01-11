package com.notifyhub.application.builder;

import com.notifyhub.domain.Messages;

public class MessagesBuilder {
    private String title;
    private String content;

    public MessagesBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MessagesBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public Messages build() {
        if (title == null || title.isEmpty()) {
            new IllegalArgumentException("Title is required");
        }
        if (content == null || content.isEmpty()) {
            new IllegalArgumentException("Content is required");
        }
        return new Messages(title, content);
    }

}
