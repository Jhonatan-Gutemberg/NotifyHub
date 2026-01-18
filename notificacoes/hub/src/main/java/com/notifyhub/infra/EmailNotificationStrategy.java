package com.notifyhub.infra;

import java.util.Properties;

import com.notifyhub.application.port.INotificationStrategy;
import com.notifyhub.domain.NotificationMessage;
import com.notifyhub.domain.Notification;
import com.notifyhub.domain.Recipient;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailNotificationStrategy implements INotificationStrategy {

    private final String smtpHost = "smtp.gmail.com";
    private final int smtpPort = 587;

    private final String username;
    private final String password;

    public EmailNotificationStrategy(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void send(Notification notification) {
        Recipient recipient = notification.getRecipient();
        NotificationMessage message = notification.getMessage();
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
                    InternetAddress.parse(recipient.getAddress()));
            email.setSubject(message.getTitle());
            email.setContent(message.getContent(), "text/html; charset=utf-8");

            Transport.send(email);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}
