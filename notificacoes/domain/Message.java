package notificacoes.domain;

public class Message {
    String id;
    String title;
    String content;

    Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    Message() {
    }

    private void settitle(String title) {
        if (title == null || title.isEmpty()) {
            return;
        }
        this.title = title;
    }

    private String gettitle() {
        return this.title;
    }

    private void setContent(String content) {
        if (content == null || content.isEmpty()) {
            return;
        }
        this.content = content;
    }

    private String getContent() {
        return this.content;
    }
}
