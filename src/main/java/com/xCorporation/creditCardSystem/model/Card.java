package com.xCorporation.creditCardSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(schema = "card")
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long card_id;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "brand")
    private String brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_holder_id")
    private CardHolder cardHolder;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
