package com.notifyhub.infra;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.Messages;
import com.notifyhub.domain.Recipient;

public class EmailNotification implements INotificationStrategy {

    private final String smtpHost = "smtp.gmail.com";
    private final int smtpPort = 587;

    private final String username;
    private final String password;

    public EmailNotification(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void send(Recipient recipient,Messages message) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(username));
            email.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient.getAddress())
            );
            email.setSubject(message.gettitle());
            email.setContent(message.getContent(),"text/html; charset=utf-8");

            Transport.send(email);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}

