package com.xCorporation.creditCardSystem.service;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.Transaction;

public interface SimpleMailSenderService {

    void sendCardMail(Card card);

    void sendTransactionEmail(Transaction transaction);
}
