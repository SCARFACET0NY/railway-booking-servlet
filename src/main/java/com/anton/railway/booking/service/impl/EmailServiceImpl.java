package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    public Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = EmailService.class.getClassLoader().getResourceAsStream("mail.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    public String createEmailText(User user, Map<Long, TicketDto> cart, BigDecimal total) {
        String lastFourDigits = user.getCardNumber().substring(user.getCardNumber().length() - 4);

        StringBuilder message = new StringBuilder("<h2>Tickets</h2>");
        for (TicketDto ticketDto : cart.values()) {
            message.append("<p>Train Number: " + ticketDto.getTripDto().getTrain().getTrainNumber());
            message.append(", From: " + ticketDto.getTripDto().getDepartureCity());
            message.append(", To: " + ticketDto.getTripDto().getArrivalCity());
            message.append(", Departure: " + ticketDto.getTripDto().getDepartureTime());
            message.append(", Arrival: " + ticketDto.getTripDto().getArrivalTime());
            message.append(", Wagon Number: " + ticketDto.getWagonNumber());
            message.append(", Seat Number: " + ticketDto.getTripSeatDto().getSeatNumber());
            message.append(", Price: " + ticketDto.getTicket().getPrice() + "</p>");
        }

        message.append("<br/>");
        message.append("<p>Buyer: " + user.getFirstName() + " " + user.getLastName() + ", ");
        message.append("Card: ******" + lastFourDigits + ", Total: " + total + "</p>");

        return message.toString();
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        Properties properties = loadProperties();
        Session mailSession = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.host")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(text, "text/html");

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
