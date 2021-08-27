package com.training.pbpay.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "transactionId", initialValue = 10001, allocationSize = 1)
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionId")
	private Long transactionId;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "source_account_number")	
	private Account sourceAccountNumber;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "beneficiaryAccountNumber", referencedColumnName = "beneficiaryAccountNo")
	private Beneficiary beneficiaryAccountNumber;
	private Double transactionAmount;
	private String transactionType;
	private LocalDateTime transactionDate;
	private String status;

}
