package com.xCorporation.creditCardSystem.exception;

public class CardHolderNotFoundException extends RuntimeException {

    public CardHolderNotFoundException(Long id) {
        super("Could not find card holder with id " + id);
    }
}
