package com.bookexchange.service;

import com.bookexchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class KafKaConsumerService
{
    private final Logger logger = Logger.getLogger(KafKaConsumerService.class.getName());

    @Autowired
    private UserService userService;

    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
    public void consume(String msg)
    {
        logger.info(String.format("Message recieved -> %s", msg));
        Long userId = Long.valueOf(msg);
        User user = userService.getUser(userId);
        String to = user.getEmail();

        String from = "support@bookexchange.com";

        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sathkumar@zuora.com", "zlnzwboijvivploo");
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Book Exchange App Email Verification");
            message.setText("Please click on the below link to confirm your registration" + "\n" + "http://localhost:8080/api/v1/users/"+msg+"/confirmUser");

            logger.log(Level.INFO, "Sending...");
            Transport.send(message);
            logger.log(Level.INFO,"Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}