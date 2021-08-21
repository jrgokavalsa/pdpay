package com.training.pbpay.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BeneficiaryRegisterDto {
	
	@NotEmpty(message = "{beneficiary.name.not.blank}")
	@Size(min = 5, max = 15)
	@Pattern(regexp = "[a-zA-Z\s]+", message = "{beneficiary.name.invalid}")
	private String beneficiaryName;
	
	@NotNull(message = "{beneficiary.account.not.null}")
    @Digits(integer = 9, fraction = 0, message = "{beneficiary.account.invalid}")
	private long beneficiaryAccountNo;

	@NotNull(message = "{benficiary.limit.not.null}")
	@DecimalMin(value = "500.00", message = "{beneficiary.limit.min}")
	@DecimalMax(value ="100000.00",message = "{beneficiary.limit.max}")
	private Double transferLimit;

	@NotEmpty(message = "{beneficiary.ifsc.not.empty}")
	@Size(min = 5, max = 15) 
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "{beneficiary.ifsc.invalid}")
	private String ifsCode;
	
	@NotNull(message = "{account.number.invalid}")
	@Digits(integer = 9, fraction = 0,message = "{account.number.invalid}")
	private Long accountNumber;
}
