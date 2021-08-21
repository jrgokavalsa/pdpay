package com.training.pbpay.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {

	@NotNull(message="{username.not.blank}")
	private Integer userId;
	@NotNull(message="{account.number.empty}")
	private Long accountNumber;
	@NotNull(message="{beneficiary.account.not.null}")
	private Long beneficiaryAccountNumber;
	@NotNull(message="{beneficiary.amount.invalid}")
	private Double transactionAmount;
}
