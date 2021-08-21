package com.training.pbpay.service;

import org.springframework.stereotype.Service;

import com.training.pbpay.constant.AppConstant;
import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.exceptions.UserAlreadyExistException;
import com.training.pbpay.exceptions.UserNotFoundException;
import com.training.pbpay.mapper.UserMapper;
import com.training.pbpay.model.User;
import com.training.pbpay.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public LoginResponseDto registerUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
		userRepository.findByUserName(signUpRequestDto.getUserName()).ifPresent(value -> {
			log.error("Entering into UserServiceImpl registerUser method:" + AppConstant.USER_ALREADY_EXIST + " "
					+ value.getUserName());
			throw new UserAlreadyExistException(AppConstant.USER_ALREADY_EXIST + value.getUserName());
		});
		User user = userMapper.map(signUpRequestDto);
		LoginResponseDto loginResponse = userMapper.mapToDto(user);
		userRepository.save(user);
		return loginResponse;
	}

	@Override
	public String authenticateUser(LoginResponseDto loginResponseDto) throws UserNotFoundException {
		userRepository.findByUserNameAndPassword(loginResponseDto.getUserName(), loginResponseDto.getPassword())
				.orElseThrow(() -> new UserNotFoundException(AppConstant.USER_NOT_FOUND));
		log.info("Authentication Successful");
		return AppConstant.AUTHENTICATION_SUCCESSFUL;
	}

}
