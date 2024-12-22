package com.xCorporation.creditCardSystem.service;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.rest.model.CardRequest;

import java.util.List;

public interface CardService {

    void register();

    void findCardsByCardHolderIdentityDocument();

    Card registerCard(CardRequest cardRequest);

    void deleteCard(Long id);

}
