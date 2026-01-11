package com.notifyhub.domain;

import java.util.Objects;

public class Messages {
    String id;
    String title;
    String content;

    public Messages(String title, String content) {
        this.title = Objects.requireNonNull(title, "title is not null");
        this.content = Objects.requireNonNull(content, "content is not null");
    }

    public Messages() {
    }

    public String gettitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
