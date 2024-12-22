package com.xCorporation.creditCardSystem.service.impl;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.Transaction;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.service.SimpleMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailSenderServiceImpl implements SimpleMailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendCardMail(Card card) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(card.getCardHolder().getEmail());
        message.setSubject("This is yout credit card information !");
        message.setText("Card number: " + card.getCardNumber() + " Card CVV: " + card.getCvv());

        mailSender.send(message);

    }

    @Override
    public void sendTransactionEmail(Transaction transaction) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(transaction.getCard().getCardHolder().getEmail());
        message.setSubject("Your purchase with id " + transaction.getTransactionId() + " was successfully processed !");
        message.setText("Detail: " + transaction.getDetail() + " Total amount: " + transaction.getTotal());

        mailSender.send(message);
    }
}
