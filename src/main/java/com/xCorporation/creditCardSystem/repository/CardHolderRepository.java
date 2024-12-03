package com.xCorporation.creditCardSystem.repository;

import com.xCorporation.creditCardSystem.model.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {

    CardHolder findByNameAndSurname(String name, String surname);
}
