package com.training.pbpay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.pbpay.controllers.UserController;
import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.exceptions.UserNotFoundException;
import com.training.pbpay.model.User;
import com.training.pbpay.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PbpayApplicationTests {
	

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	User user = new User();
	SignUpRequestDto request = new SignUpRequestDto();
	LoginResponseDto loginRequestDto = new LoginResponseDto();

	@BeforeAll
	public void setUp() {

		loginRequestDto.setUserName("JR9281");
		loginRequestDto.setPassword("jr0721");
		
		user.setUserId(1);
		user.setUserName("JR9281");
		user.setFirstName("Arun Kumar");
		user.setLastName("Karthikeya");
		user.setMobile("1234567890");
		user.setEmail("jr@gmail.com");
		user.setPanCard("XBSXJ7486G");
		user.setAadharCard("3675 9834 6012");
		user.setPassword("jr0721");
		user.setAddress("Hyderabad");
		user.setCreated(Instant.now());
		user.setEnabled(true);
		
		request.setUserName("JR9281");
		request.setFirstName("Arun Kumar");
		request.setLastName("Karthikeya");
		request.setMobile("1234567890");
		request.setEmail("jr@gmail.com");
		request.setPanCard("BJEPG7486G");
		request.setAadharCard("3675 9834 6012");
		request.setAddress("Hyderabad");
		

	}
	
	@Test
	@DisplayName("User Registration Test")
	public void signUpTest()  {
		Mockito.when(userService.registerUser(request)).thenReturn(loginRequestDto);
		ResponseEntity<LoginResponseDto> result = userController.signUpUser(request);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}

	@Test
	@DisplayName("Login Registration Test")
	public void loginTest() throws UserNotFoundException {
		Mockito.when(userService.authenticateUser(loginRequestDto)).thenReturn("Authentication Successful");
		ResponseEntity<String> result = userController.login(loginRequestDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}
}
