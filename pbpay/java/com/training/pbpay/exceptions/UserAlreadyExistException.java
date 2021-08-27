package com.training.pbpay.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = -622721375808999441L;

    public UserAlreadyExistException(String message) {
        super(message);
    }
}


