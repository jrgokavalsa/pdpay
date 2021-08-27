package com.training.pbpay.exceptions;

public class TransactionLimitExceedsException extends RuntimeException{

	private static final long serialVersionUID = 5823012641432105065L;

	public TransactionLimitExceedsException(String message) {
		super(message);
	}

	
	
}
