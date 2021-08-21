package com.training.pbpay.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3927260383982173659L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
