package com.xCorporation.creditCardSystem.repository.views;

import java.time.LocalDate;

public interface PrivateCardView {

    String getBrand();

    Long getCardNumber();

    LocalDate getExpirationDate();

    String getCardHolderName();

    String getCardHolderSurname();
}
