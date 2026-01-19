package com.notifyhub.application.templates;

public class DesignPatternsEmailTemplate extends HtmlEmailTemplate {

    @Override
    protected void buildBody(StringBuilder html) {

        html.append("        <div class=\"header\">\n")
            .append("            <h2 style=\"margin:0; color:#007bff;\">Padrões de Projeto</h2>\n")
            .append("        </div>\n")
            .append("        <p>Olá, <strong>{{nome}}</strong>.</p>\n")
            .append("        <p>Os padrões de projeto (Design Patterns) são soluções testadas para desafios recorrentes no desenvolvimento de software. Eles ajudam a manter o sistema escalável e fácil de manter.</p>\n")
            .append("        <ul style=\"padding-left: 20px;\">\n")
            .append("            <li><strong>Criacionais:</strong> Focam na criação de objetos.</li>\n")
            .append("            <li><strong>Estruturais:</strong> Lidam com a composição de classes.</li>\n")
            .append("            <li><strong>Comportamentais:</strong> Gerenciam a comunicação entre objetos.</li>\n")
            .append("        </ul>\n")
            .append("        <p>Abaixo, você pode acessar o material completo sobre o tema:</p>\n")
            .append("        <div style=\"text-align: center; margin: 30px 0;\">\n")
            .append("            <a href=\"https://seusite.com.br\" class=\"btn\" style=\"color: #ffffff; text-decoration: none;\">Acessar Conteúdo</a>\n")
            .append("        </div>\n")
            .append("        <div class=\"footer\">\n")
            .append("            Este é um informativo técnico automático.<br>\n")
            .append("            Por favor, não responda a este e-mail.\n")
            .append("        </div>\n");
    }
}
