package com.xCorporation.creditCardSystem.rest.converters;

import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.rest.model.CardHolderRequest;
import com.xCorporation.creditCardSystem.rest.model.CardHolderResponse;

public class CardHolderConverter {

    public static CardHolder convert(CardHolderRequest source) {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName(source.getName());
        cardHolder.setSurname(source.getSurname());
        cardHolder.setIdentityDocument(source.getIdentityDocument());
        cardHolder.setBirthDate(source.getBirthDate());
        cardHolder.setEmail(source.getEmail());
        return cardHolder;
    }

    public static CardHolderResponse convert(CardHolder source) {
        return CardHolderResponse.builder()
                .id(source.getCardHolderId())
                .name(source.getName())
                .surname(source.getSurname())
                .email(source.getEmail())
                .birthDate(source.getBirthDate())
                .identityDocument(source.getIdentityDocument())
                .build();
    }
}
