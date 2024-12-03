package com.xCorporation.creditCardSystem.converters;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.PrivateCard;
import com.xCorporation.creditCardSystem.repository.views.PrivateCardView;

import java.util.List;

public class PrivateCardViewConverter {

    public static PrivateCard convert(PrivateCardView source) {
        return PrivateCard.builder()
                .brand(source.getBrand())
                .cardNumber(source.getCardNumber())
                .expirationDate(source.getExpirationDate())
                .cardHolderName(source.getCardHolderName())
                .cardHolderSurname(source.getCardHolderSurname())
                .build();
    }

    public static List<PrivateCard> convert(List<PrivateCardView> source) {
        return source.stream().map(PrivateCardViewConverter::convert).toList();
    }
}
