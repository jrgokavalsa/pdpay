package com.training.pbpay.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beneficiaryId;

	@NotEmpty(message = "{beneficiary.name.not.blank}")
	@Size(min = 5, max = 15)
	@Pattern(regexp = "[a-zA-Z\s]+", message = "{beneficiary.name.invalid}")
	private String beneficiaryName;

	@NotNull(message = "{beneficiary.account.not.null}")
	@Digits(integer = 9, fraction = 0, message = "{beneficiary.account.invalid}")
	@Positive(message = "{beneficiary.account.invalid}")
	private long beneficiaryAccountNo;

	@NotNull(message = "{beneficiary.limit.not.null}")
	@DecimalMin(value = "500.00", message = "{beneficiary.balance.min}")
	@DecimalMax(value ="100000.00",message = "{beneficiary.balance.max}")
	private Double transferLimit;

	@NotEmpty(message = "{beneficiary.ifsc.not.empty}")
	@Size(min = 5, max = 15) 
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "{beneficiary.ifsc.invalid}")
	private String ifsCode;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "accountNumber")
	private Account account;
}
