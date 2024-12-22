package com.xCorporation.creditCardSystem.repository;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.repository.views.PrivateCardView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c.brand as brand, c.cardNumber as cardNumber, c.expirationDate as expirationDate, c.cardHolder.name as cardHolderName, " +
            "c.cardHolder.surname as cardHolderSurname from Card c where c.cardHolder.identityDocument = :identityDocument")
    List<PrivateCardView> findAllByCardHolderIdentityDocument(String identityDocument);

    Optional<Card> findByCardNumber(Long cardNumber);
}
