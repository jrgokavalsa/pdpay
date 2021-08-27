package com.training.pbpay.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterDto {
	@NotNull(message = "{user.invalid}")
	private Integer userId;
	@NotEmpty(message = "{account.bank.empty}")
	private String bankName;
	@NotEmpty(message = "{account.branch.empty}")
	private String branchName;
	@DecimalMin(value = "3000.00", message = "{account.balance.min}")
	private Double balance;
	@NotEmpty(message = "{account.ifsc.not.empty}")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "{account.ifsc.invalid}")
	private String ifsCode;

}
