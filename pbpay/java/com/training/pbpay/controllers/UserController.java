package com.training.pbpay.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginResponseDto loginResponseDto){
		log.info("Entering into UserController login method" );
		return ResponseEntity.status(HttpStatus.OK).body(userService.authenticateUser(loginResponseDto));
	}

	@PostMapping("/signup")
	public ResponseEntity<LoginResponseDto> signUpUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
		log.info("Entering into UserController signUser method" );
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(signUpRequestDto));
	}
	
	

}
