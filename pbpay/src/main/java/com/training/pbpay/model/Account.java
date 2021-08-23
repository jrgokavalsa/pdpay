package com.training.pbpay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "accountNumber", initialValue = 50000001, allocationSize = 1)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountNumber")
	private Long accountNumber;
	
	@NotEmpty(message = "{account.bank.empty}")
	private String bankName;
	
	@NotEmpty(message = "{account.branch.empty}")
	private String branchName;
	
	@DecimalMin(value = "3000.00", message = "{account.balance.min}")
	@DecimalMax(value ="100000.00",message = "{account.balance.max}")
	private Double balance;
	
	@NotEmpty(message = "{account.ifsc.not.empty}")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "{account.ifsc.invalid}")
	private String ifsCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
	
	@OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
	private List<Beneficiary> beneficiaries;
}
