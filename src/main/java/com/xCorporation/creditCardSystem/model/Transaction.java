package com.xCorporation.creditCardSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "comission_rate")
    private Double comisionRate;

    @Column(name = "total")
    private Double total;

    @Column(name = "detail")
    private String detail;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;
}
