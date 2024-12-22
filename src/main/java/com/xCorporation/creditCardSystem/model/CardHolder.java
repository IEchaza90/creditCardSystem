package com.xCorporation.creditCardSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(schema = "card_holder")
@NoArgsConstructor
@AllArgsConstructor
public class CardHolder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "card_holder_id")
    private Long cardHolderId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "identity_document")
    private String identityDocument;

    @Column(name = "email")
    private String email;
}
