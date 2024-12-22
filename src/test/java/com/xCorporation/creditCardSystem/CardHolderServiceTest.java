package com.xCorporation.creditCardSystem;

import com.xCorporation.creditCardSystem.model.CardHolder;
import com.xCorporation.creditCardSystem.repository.CardHolderRepository;
import com.xCorporation.creditCardSystem.rest.converters.CardHolderConverter;
import com.xCorporation.creditCardSystem.rest.model.CardHolderRequest;
import com.xCorporation.creditCardSystem.service.CardHolderService;
import com.xCorporation.creditCardSystem.service.impl.CardHolderServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CardHolderServiceTest {

    @Mock
    private CardHolderRepository cardHolderRepository;

    private CardHolderService cardHolderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cardHolderService = new CardHolderServiceImpl(cardHolderRepository);
        Mockito.when(cardHolderRepository.save(Mockito.any())).thenReturn(new CardHolder());
    }

    @Test
    public void createCardHolder_ok() {

        cardHolderService.registerCardHolder(buildCardHolderRequest());
        Mockito.verify(cardHolderRepository, Mockito.times(1)).save(Mockito.any());
    }

    private CardHolderRequest buildCardHolderRequest() {
        CardHolderRequest cardHolderRequest = new CardHolderRequest();
        cardHolderRequest.setName("name");
        cardHolderRequest.setSurname("surname");
        cardHolderRequest.setEmail("email@email.com");
        cardHolderRequest.setIdentityDocument("AB1234567");
        cardHolderRequest.setBirthDate(LocalDate.now());
        return cardHolderRequest;
    }
}
