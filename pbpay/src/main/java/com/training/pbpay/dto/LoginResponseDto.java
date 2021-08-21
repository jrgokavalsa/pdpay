package com.training.pbpay.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

	@NotBlank(message = "{user.not.blank}")
	private String userName;
	@NotBlank(message = "{password.not.blank}")
	private String password;
}
