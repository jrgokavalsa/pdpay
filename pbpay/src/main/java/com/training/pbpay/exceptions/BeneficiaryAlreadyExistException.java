package com.training.pbpay.exceptions;

public class BeneficiaryAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = -5243164076348735435L;

    public BeneficiaryAlreadyExistException(String message) {
        super(message);
    }
}


