package com.training.pbpay.service;

import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.dto.TransactionRequestDto;
import com.training.pbpay.exceptions.AccountNotFoundException;
import com.training.pbpay.exceptions.UserNotFoundException;

public interface AccountService {
	String registerAccount(AccountRegisterDto accountRegisterDto);

	String registerBeneficiary(BeneficiaryRegisterDto beneficiaryRegisterDto);

	String transferCurrency(TransactionRequestDto transactionRequestDto)throws UserNotFoundException, AccountNotFoundException;
}
