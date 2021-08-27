package com.training.pbpay.exceptions;

public class InsufficientBalanceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507082294066856782L;

	public InsufficientBalanceException(String message) {
		super(message);
	}

	
}
