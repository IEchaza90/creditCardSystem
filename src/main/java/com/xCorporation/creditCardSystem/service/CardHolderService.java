package com.xCorporation.creditCardSystem.service;

import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.rest.model.CardHolderRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface CardHolderService {

    void register();

    CardHolder registerCardHolder(CardHolderRequest cardHolderRequest);

    CardHolder modifyCardHolder(CardHolderRequest cardHolderRequest, Long id);

    void deleteCardHolder(Long id);

    CardHolder findById(Long id);

    CardHolder findByNameAndSurname(String name, String surname);
}
