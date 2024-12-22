package com.xCorporation.creditCardSystem.rest.converters;

import com.xCorporation.creditCardSystem.rest.model.CardResponse;
import com.xCorporation.creditCardSystem.repository.views.PrivateCardView;

import java.util.List;

public class PrivateCardViewConverter {

    public static CardResponse convert(PrivateCardView source) {
        return CardResponse.builder()
                .brand(source.getBrand())
                .cardNumber(source.getCardNumber())
                .expirationDate(source.getExpirationDate())
                .cardHolderName(source.getCardHolderName())
                .cardHolderSurname(source.getCardHolderSurname())
                .build();
    }

    public static List<CardResponse> convert(List<PrivateCardView> source) {
        return source.stream().map(PrivateCardViewConverter::convert).toList();
    }
}
