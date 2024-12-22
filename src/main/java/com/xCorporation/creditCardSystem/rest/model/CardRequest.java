package com.xCorporation.creditCardSystem.rest.model;

import com.xCorporation.creditCardSystem.model.CardHolder;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CardRequest {

    private String brand;

    private Long cardNumber;

    private Long cardHolderId;

    private LocalDate expirationDate;
}
