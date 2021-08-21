package com.training.pbpay.service;

import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.exceptions.UserAlreadyExistException;
import com.training.pbpay.exceptions.UserNotFoundException;

public interface UserService {
	LoginResponseDto registerUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException;

	String authenticateUser( LoginResponseDto loginResponseDto) throws UserNotFoundException;

}
