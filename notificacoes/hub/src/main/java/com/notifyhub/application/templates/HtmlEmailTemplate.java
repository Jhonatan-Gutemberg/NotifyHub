package com.notifyhub.application.templates;

public abstract class HtmlEmailTemplate {

    protected String recipient;

    public HtmlEmailTemplate recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public final String build() {
        StringBuilder html = new StringBuilder();

        buildHeader(html);
        buildBody(html);
        buildFooter(html);

        return html.toString()
                   .replace("{{nome}}", recipient);
    }

    protected void buildHeader(StringBuilder html) {
        html.append("<!DOCTYPE html>\n")
            .append("<html>\n")
            .append("<head>\n")
            .append("    <meta charset=\"UTF-8\">\n")
            .append("    <style>\n")
            .append("        body { font-family: sans-serif; line-height: 1.5; color: #333; margin: 0; padding: 0; }\n")
            .append("        .container { max-width: 500px; margin: 20px auto; padding: 20px; border: 1px solid #eee; border-radius: 5px; }\n")
            .append("        .header { border-bottom: 2px solid #007bff; padding-bottom: 10px; margin-bottom: 20px; }\n")
            .append("        .btn { background-color: #007bff; color: black; padding: 10px 20px; text-decoration: none; border-radius: 4px; display: inline-block; }\n")
            .append("        .footer { margin-top: 30px; font-size: 12px; color: #888; border-top: 1px solid #eee; padding-top: 10px; }\n")
            .append("    </style>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("    <div class=\"container\">\n");
    }

    protected abstract void buildBody(StringBuilder html);

    protected void buildFooter(StringBuilder html) {
        html.append("    </div>\n")
            .append("</body>\n")
            .append("</html>");
    }
}
