package com.training.pbpay.mapper;

import java.util.Random;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.pbpay.dto.LoginResponseDto;
import com.training.pbpay.dto.SignUpRequestDto;
import com.training.pbpay.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "password", expression = "java(passwordGenerator())")
	@Mapping(target = "created", expression = "java(java.time.Instant.now())")
	@Mapping(target = "enabled", ignore = true)
	User map(SignUpRequestDto signUpRequestDto);
	
	LoginResponseDto mapToDto(User user);
	
	default String passwordGenerator() {
		String password = new Random().ints(8, 33, 122).collect(StringBuilder::new,
		        StringBuilder::appendCodePoint, StringBuilder::append)
		        .toString();
		return password;
	}
}
