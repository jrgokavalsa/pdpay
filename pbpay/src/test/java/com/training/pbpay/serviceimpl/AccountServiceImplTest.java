package com.training.pbpay.serviceimpl;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.exceptions.BeneficiaryAlreadyExistException;
import com.training.pbpay.exceptions.UserNotFoundException;
import com.training.pbpay.mapper.AccountMapper;
import com.training.pbpay.mapper.BeneficiaryMapper;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.Beneficiary;
import com.training.pbpay.model.User;
import com.training.pbpay.repository.AccountRepository;
import com.training.pbpay.repository.BeneficiaryRepository;
import com.training.pbpay.repository.UserRepository;
import com.training.pbpay.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@Mock
	AccountMapper accountMapper;

	@Mock
	BeneficiaryMapper beneficiaryMapper;

	static AccountRegisterDto accountRegisterDto;
	static User user;
	static Account account;
	static Beneficiary beneficiary;
	static BeneficiaryRegisterDto beneficiaryRegisterDto;

	@BeforeAll
	public static void setup() {
		accountRegisterDto = new AccountRegisterDto();

		accountRegisterDto.setUserId(1);
		accountRegisterDto.setBankName("HDFC Bank");
		accountRegisterDto.setBranchName("HYDERABAD");
		accountRegisterDto.setBalance(30000d);
		accountRegisterDto.setIfsCode("HDFC0000109");

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

		account = new Account();
		account.setAccountNumber(50000001L);
		account.setBalance(30000d);
		account.setBankName("HDFC Bank");
		account.setBranchName("Hyderabad");
		account.setIfsCode("HDFC0000109");
		account.setUser(user);

		beneficiaryRegisterDto = new BeneficiaryRegisterDto();
		beneficiaryRegisterDto.setAccountNumber(50000001L);
		beneficiaryRegisterDto.setBeneficiaryAccountNo(58964562L);
		beneficiaryRegisterDto.setBeneficiaryName("Sudharshan Reddy");
		beneficiaryRegisterDto.setIfsCode("SBIN0125620");
		beneficiaryRegisterDto.setTransferLimit(50000d);

		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setAccount(account);
		beneficiary.setBeneficiaryAccountNo(58964562L);
		beneficiary.setBeneficiaryName("Sudharshan Reddy");
		beneficiary.setIfsCode("SBIN0125620");
		beneficiary.setTransferLimit(50000d);
	}

	@Test
	@DisplayName("Account Registration : Positive Scenario")
	public void registerAccountTest() {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(accountMapper.map(accountRegisterDto, user)).thenReturn(account);
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenAnswer(i -> {
			Account account = i.getArgument(0);
			account.setAccountNumber(50000001L);
			return account;
		});
		String actual = accountServiceImpl.registerAccount(accountRegisterDto);
		org.junit.jupiter.api.Assertions.assertEquals("Account Registered Successfully " + account.getAccountNumber(),
				actual);
	}

	@Test
	@DisplayName("Account Registration : Negative Scenario")
	public void registerAccountTestNegative() {
		Mockito.when(userRepository.findById(1)).thenThrow(UserNotFoundException.class);
		org.junit.jupiter.api.Assertions.assertThrows(UserNotFoundException.class,
				() -> accountServiceImpl.registerAccount(accountRegisterDto));
	}

	@Test
	@DisplayName("Beneficiary Registration : Positive Scenario")
	public void registerBeneficiaryTestPositive() {
		Mockito.when(accountRepository.findById(50000001L)).thenReturn(Optional.of(account));
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountNoAndAccountAccountNumber(58964562L, 50000001L))
				.thenReturn(Optional.ofNullable(null));
		Mockito.when(beneficiaryMapper.map(beneficiaryRegisterDto, account)).thenReturn(beneficiary);
		Mockito.when(beneficiaryRepository.save(Mockito.any(Beneficiary.class))).thenAnswer(j -> {
			Beneficiary beneficiary = j.getArgument(0);
			beneficiary.setBeneficiaryId(1L);
			return beneficiary;
		});
		String actual = accountServiceImpl.registerBeneficiary(beneficiaryRegisterDto);
		Assertions.assertEquals("Beneficiary Registered Successfully", actual);
	}

	@Test
	@DisplayName("Beneficiary Registration : Negative Scenario")
	public void registerBeneficiaryTestNegative() {
		Mockito.when(accountRepository.findById(50000001L)).thenReturn(Optional.of(account));
		Mockito.when(beneficiaryRepository.findByBeneficiaryAccountNoAndAccountAccountNumber(58964562L, 50000001L))
				.thenReturn(Optional.of(beneficiary));
		Assertions.assertThrows(BeneficiaryAlreadyExistException.class,
				() -> accountServiceImpl.registerBeneficiary(beneficiaryRegisterDto));
	}

}
