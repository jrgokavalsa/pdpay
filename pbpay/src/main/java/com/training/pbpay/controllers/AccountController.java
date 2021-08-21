package com.training.pbpay.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.dto.TransactionRequestDto;
import com.training.pbpay.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/")
	public ResponseEntity<String> registerAccount(@Valid @RequestBody AccountRegisterDto accountRegisterDto){
		log.info("Entering into AccountController register account method" );
		return ResponseEntity.status(HttpStatus.OK).body(accountService.registerAccount(accountRegisterDto));
	}
	
	@PostMapping("/beneficiaries")
	public ResponseEntity<String> registerBeneficiary(@Valid @RequestBody BeneficiaryRegisterDto beneficiaryRegisterDto){
		log.info("Entering into AccountController register account method" );
		return ResponseEntity.status(HttpStatus.OK).body(accountService.registerBeneficiary(beneficiaryRegisterDto));
	}
	@PostMapping("/transactions")
	public ResponseEntity<String> transferCurrency(@Valid @RequestBody TransactionRequestDto transactionRequestDto){
		log.info("Entering into transferCurrency of AccountController");
		return ResponseEntity.status(HttpStatus.OK).body(accountService.transferCurrency(transactionRequestDto));
	}
}
