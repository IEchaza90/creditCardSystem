package com.xCorporation.creditCardSystem;

import com.xCorporation.creditCardSystem.model.Card;
import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.repository.CardRepository;
import com.xCorporation.creditCardSystem.rest.model.CardRequest;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.service.CardService;
import com.xCorporation.creditCardSystem.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardHolderService cardHolderService;

    private CardService cardService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cardService = new CardServiceImpl(cardRepository, cardHolderService);
        Mockito.when(cardHolderService.findById(Mockito.any())).thenReturn(new CardHolder());
        Mockito.when(cardRepository.save(Mockito.any())).thenReturn(new Card());
    }

    @Test
    public void createCard_ok() {
        cardService.registerCard(buildCardRequest());
        Mockito.verify(cardRepository, Mockito.times(1)).save(Mockito.any());
    }

    private CardRequest buildCardRequest() {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setCardNumber(123456L);
        cardRequest.setCardHolderId(1L);
        cardRequest.setBrand("VISA");
        cardRequest.setExpirationDate(LocalDate.now().plusDays(1));
        return cardRequest;
    }
}
