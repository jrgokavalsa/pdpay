package com.training.pbpay.controller;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.pbpay.controllers.AccountController;
import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.dto.TransactionRequestDto;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.User;
import com.training.pbpay.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

	@InjectMocks
	AccountController accountController;

	@Mock
	AccountService accountService;

	static AccountRegisterDto accountRegisterDto;
	static BeneficiaryRegisterDto beneficiaryRegisterDto;
	static TransactionRequestDto transactionRequestDto;
	static Account account;
	static User user;

	@BeforeAll
	public static void setUp() {

		accountRegisterDto = new AccountRegisterDto();

		accountRegisterDto.setUserId(1);
		accountRegisterDto.setBankName("HDFC Bank");
		accountRegisterDto.setBranchName("HYDERABAD");
		accountRegisterDto.setBalance(30000d);
		accountRegisterDto.setIfsCode("HDFC0000109");

		account = new Account();
		account.setAccountNumber(50000001L);
		account.setBalance(30000d);
		account.setBankName("HDFC Bank");
		account.setBranchName("Hyderabad");
		account.setIfsCode("HDFC0000109");
		account.setUser(user);

		user = new User();
		user.setUserId(1);
		user.setUserName("JR9281");
		user.setFirstName("Arun Kumar");
		user.setLastName("Karthikeya");
		user.setMobile("1234567890");
		user.setEmail("jr@gmail.com");
		user.setPanCard("XBSXJ7486G");
		user.setAadharCard("3675 9834 6012");
		user.setPassword("jr0721");
		user.setAddress("Hyderabad");
		user.setCreated(Instant.now());
		user.setEnabled(true);
		
		beneficiaryRegisterDto = new BeneficiaryRegisterDto();
		beneficiaryRegisterDto.setAccountNumber(50000001l);
		beneficiaryRegisterDto.setBeneficiaryAccountNo(58964562);
		beneficiaryRegisterDto.setBeneficiaryName("Karthik");
		beneficiaryRegisterDto.setIfsCode("HDFC0000109");
		beneficiaryRegisterDto.setTransferLimit(50000d);
	}

	@Test
	@DisplayName("Account Registration Test")
	public void registerAccountTest() {
		Mockito.when(accountService.registerAccount(accountRegisterDto))
				.thenReturn("Account Registered Successfully " + account.getAccountNumber());
		ResponseEntity<String> result= accountController.registerAccount(accountRegisterDto);
		Assertions.assertEquals(HttpStatus.CREATED,result.getStatusCode());

	}
	
	@Test
	@DisplayName("Beneficiary Registration Test")
	public void registerBeneficiaryTest() {
		Mockito.when(accountService.registerBeneficiary(beneficiaryRegisterDto))
				.thenReturn("Beneficiary Registered Successfully");
		ResponseEntity<String> result= accountController.registerBeneficiary(beneficiaryRegisterDto);
		Assertions.assertEquals(HttpStatus.CREATED,result.getStatusCode());
	}
	
	@Test
	@DisplayName("Transfer Currency Test")
	public void transferCurrency() {
		Mockito.when(accountService.transferCurrency(transactionRequestDto)).thenReturn("SUCCESS");
		ResponseEntity<String> result = accountController.transferCurrency(transactionRequestDto);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
