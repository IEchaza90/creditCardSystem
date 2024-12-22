package com.xCorporation.creditCardSystem.rest.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardHolderRequest {

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String identityDocument;

    private String email;
}
