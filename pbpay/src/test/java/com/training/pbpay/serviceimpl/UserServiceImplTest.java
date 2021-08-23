package com.training.pbpay.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.exceptions.UserNotFoundException;
import com.training.pbpay.mapper.UserMapper;
import com.training.pbpay.model.User;
import com.training.pbpay.repository.UserRepository;
import com.training.pbpay.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserMapper userMapper;

	static SignUpRequestDto signUpRequestDto;
	static User user;
	static LoginResponseDto loginRequestDto;

	@BeforeAll
	public static void setUp() {
		signUpRequestDto = new SignUpRequestDto();

		signUpRequestDto.setUserName("JR9281");
		signUpRequestDto.setFirstName("Janaki Rao");
		signUpRequestDto.setLastName("Gokavalsa");
		signUpRequestDto.setMobile("9550777402");
		signUpRequestDto.setEmail("jr@gmail.com");
		signUpRequestDto.setPanCard("BJEPG7486G");
		signUpRequestDto.setAadharCard("3675 9834 6012");
		signUpRequestDto.setAddress("Hyderabad");

		loginRequestDto = new LoginResponseDto();

		loginRequestDto.setUserName("JR9281");
		loginRequestDto.setPassword("jr0721");

		user = new User();
		user.setUserId(1);
		user.setUserName("JR9281");
		user.setFirstName("Janaki Rao");
		user.setLastName("Gokavalsa");
		user.setMobile("9550777402");
		user.setEmail("jr@gmail.com");
		user.setPanCard("BJEPG7486G");
		user.setAadharCard("3675 9834 6012");
		user.setPassword("jr0721");
		user.setAddress("Hyderabad");
		user.setCreated(Instant.now());
		user.setEnabled(true);
	}

	@Test
	@DisplayName("Register UserDetails")
	public void registerUserTest() {

		when(userMapper.map(signUpRequestDto)).thenReturn(user);

		when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> {
			User user = i.getArgument(0);
			user.setUserId(1);
			return user;
		});
		when(userMapper.mapToDto(user)).thenReturn(loginRequestDto);

		LoginResponseDto actual = userServiceImpl.registerUser(signUpRequestDto);
		assertEquals("JR9281", actual.getUserName());
	}

	@Test
	@DisplayName("authentication : positive scenario")
	public void authenticateUserTestPostive() {
		when(userRepository.findByUserNameAndPassword("JR9281", "jr0721")).thenReturn(Optional.of(user));

		String response = userServiceImpl.authenticateUser(loginRequestDto);

		assertEquals("Authentication Successful", response);
	}
	
	@Test
	@DisplayName("authentication : negative scenario")
	public void authenticateUserTestNegative() {
		when(userRepository.findByUserNameAndPassword("JR9281", "jr0721")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class,()->userServiceImpl.authenticateUser(loginRequestDto));
	}
}
