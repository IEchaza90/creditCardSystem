package com.xCorporation.creditCardSystem.rest.converters;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.rest.model.CardRequest;
import com.xCorporation.creditCardSystem.rest.model.CardResponse;

public class CardConverter {

    public static Card convert(CardRequest cardRequest, CardHolder cardHolder) {
        Card card = new Card();
        card.setBrand(cardRequest.getBrand());
        card.setCardNumber(cardRequest.getCardNumber());
        card.setExpirationDate(cardRequest.getExpirationDate());
        card.setCardHolder(cardHolder);
        return card;
    }

    public static CardResponse convert(Card source) {
        return CardResponse.builder()
                .brand(source.getBrand())
                .cardNumber(source.getCardNumber())
                .cardHolderName(source.getCardHolder().getName())
                .cardHolderSurname(source.getCardHolder().getSurname())
                .expirationDate(source.getExpirationDate())
                .build();
    }
}
