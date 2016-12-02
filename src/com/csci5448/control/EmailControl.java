package com.csci5448.control;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailControl {

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String CREDENTIALS_PATH = "credentials.txt";

    private static EmailControl emailControl;

    private final String emailAddress;
    private final String password;
    private final Map<String, String> emailProperties;

    public static EmailControl getEmailControl() {
        if (emailControl == null) {
            try {
                emailControl = new EmailControl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return emailControl;
    }

    private EmailControl() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(CREDENTIALS_PATH)))) {
            emailAddress = in.readLine();
            password = in.readLine();
        }

        emailProperties = new HashMap<String, String>() {{
            put("mail.smtps.host", "smpt.gmail.com");
            put("mail.smtp.socketFactory.class", SSL_FACTORY);
            put("mail.smtp.socketFactory.fallback", "false");
            put("mail.smtp.port", "465");
            put("mail.smtp.socketFactory.port", "465");
            put("mail.smtps.auth", "true");
            put("mail.smtps.quitwait", "false");
        }};
    }

    public void sendEmail(String recipient, String subject, String body) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = System.getProperties();
        for (Map.Entry<String, String> property : emailProperties.entrySet()) {
            props.put(property.getKey(), property.getValue());
        }

        Session session = Session.getInstance(props, null);

        final MimeMessage message = new MimeMessage(session);
        message.setFrom(emailAddress);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
        message.setSubject(subject);
        message.setText(body);
        message.setSentDate(new Date());

        SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
        try {
            transport.connect("smtp.gmail.com", emailAddress, password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }

    public void sendSelfEmail(String subject, String body) throws MessagingException {
        sendEmail(emailAddress, subject, body);
    }

    public boolean isEmailValid(String emailAddress) {
        try {
            InternetAddress address = new InternetAddress(emailAddress);
            address.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

}
