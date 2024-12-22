package com.xCorporation.creditCardSystem.rest.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CardHolderResponse {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String identityDocument;

    private String email;
}
