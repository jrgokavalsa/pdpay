package com.training.pbpay.exceptions;

public class AccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2928504309778195684L;

    public AccountNotFoundException(String message) {
        super(message);
    }
}


