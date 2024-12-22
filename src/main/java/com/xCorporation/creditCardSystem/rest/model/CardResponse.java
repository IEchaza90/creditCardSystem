package com.xCorporation.creditCardSystem.rest.model;

import com.xCorporation.creditCardSystem.utils.DateUtils;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CardResponse {

    private Long cardNumber;

    private String brand;

    private LocalDate expirationDate;

    private String cardHolderName;

    private String cardHolderSurname;

    @Override
    public String toString() {
        return "Brand: " + this.brand + "\nCard Number: " + this.cardNumber + "\nExpiration Date: "
                + DateUtils.parseToString(this.expirationDate) + "\nCard Holder Full Name: " + this.cardHolderName + " " + this.cardHolderSurname;
    }
}
