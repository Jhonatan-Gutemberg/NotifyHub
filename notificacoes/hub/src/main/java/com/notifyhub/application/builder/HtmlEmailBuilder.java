package com.notifyhub.application.builder;

public class HtmlEmailBuilder {
    String recipient;

    public HtmlEmailBuilder setRecipient(String recipient) {
        this.recipient = recipient;

        return this;
    }

    public String build() {
        String template = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <style>\n" +
                "        body { font-family: sans-serif; line-height: 1.5; color: #333; margin: 0; padding: 0; }\n" +
                "        .container { max-width: 500px; margin: 20px auto; padding: 20px; border: 1px solid #eee; border-radius: 5px; }\n"
                +
                "        .header { border-bottom: 2px solid #007bff; padding-bottom: 10px; margin-bottom: 20px; }\n" +
                "        .btn { background-color: #007bff; color: black; padding: 10px 20px; text-decoration: none; border-radius: 4px; display: inline-block; }\n"
                +
                "        .footer { margin-top: 30px; font-size: 12px; color: #888; border-top: 1px solid #eee; padding-top: 10px; }\n"
                +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h2 style=\"margin:0; color:#007bff;\">Padrões de Projeto</h2>\n" +
                "        </div>\n" +
                "        <p>Olá, <strong>{{nome}}</strong>.</p>\n" +
                "        <p>Os padrões de projeto (Design Patterns) são soluções testadas para desafios recorrentes no desenvolvimento de software. Eles ajudam a manter o sistema escalável e fácil de manter.</p>\n"
                +
                "        \n" +
                "        <ul style=\"padding-left: 20px;\">\n" +
                "            <li><strong>Criacionais:</strong> Focam na criação de objetos.</li>\n" +
                "            <li><strong>Estruturais:</strong> Lidam com a composição de classes.</li>\n" +
                "            <li><strong>Comportamentais:</strong> Gerenciam a comunicação entre objetos.</li>\n" +
                "        </ul>\n" +
                "        \n" +
                "        <p>Abaixo, você pode acessar o material completo sobre o tema:</p>\n" +
                "        <div style=\"text-align: center; margin: 30px 0;\">\n" +
                "            <a href=\"https://seusite.com.br\" class=\"btn\" style=\"color: #ffffff; text-decoration: none;\">Acessar Conteúdo</a>\n"
                + "        </div>\n" +
                "        \n" +
                "        <div class=\"footer\">\n" +
                "            Este é um informativo técnico automático.<br>\n" +
                "            Por favor, não responda a este e-mail.\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return template
                .replace("{{nome}}", this.recipient);

    }

}
