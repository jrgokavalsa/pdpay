package com.training.pbpay.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterDto {
	@NotNull(message = "{user.invalid}")
	@ApiModelProperty(value = "userId", position = 1)
	private Integer userId;
	@NotEmpty(message = "{account.bank.empty}")
	@ApiModelProperty(value = "bankName", position = 2)
	private String bankName;
	@NotEmpty(message = "{account.branch.empty}")
	@ApiModelProperty(value = "branchName", position = 3)
	private String branchName;
	@DecimalMin(value = "3000.00", message = "{account.balance.min}")
	@DecimalMax(value = "100000.00", message = "{account.balance.max}")
	@ApiModelProperty(value = "balance", position = 4)
	private Double balance;
	@NotEmpty(message = "{account.ifsc.not.empty}")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "{account.ifsc.invalid}")
	@ApiModelProperty(value = "ifsCode", position = 5)
	private String ifsCode;

}
