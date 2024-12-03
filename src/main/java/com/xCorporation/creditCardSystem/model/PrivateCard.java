package com.xCorporation.creditCardSystem.model;

import com.xCorporation.creditCardSystem.utils.DateUtils;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class PrivateCard {

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
