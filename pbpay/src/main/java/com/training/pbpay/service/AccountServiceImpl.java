package com.training.pbpay.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.pbpay.constant.AppConstant;
import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.dto.TransactionRequestDto;
import com.training.pbpay.exceptions.AccountNotFoundException;
import com.training.pbpay.exceptions.BeneficiaryAlreadyExistException;
import com.training.pbpay.exceptions.BeneficiaryNotFoundException;
import com.training.pbpay.exceptions.InsufficientBalanceException;
import com.training.pbpay.exceptions.TransactionLimitExceedsException;
import com.training.pbpay.exceptions.UserNotFoundException;
import com.training.pbpay.mapper.AccountMapper;
import com.training.pbpay.mapper.BeneficiaryMapper;
import com.training.pbpay.mapper.TransactionMapper;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.Beneficiary;
import com.training.pbpay.model.Transaction;
import com.training.pbpay.model.User;
import com.training.pbpay.repository.AccountRepository;
import com.training.pbpay.repository.BeneficiaryRepository;
import com.training.pbpay.repository.TransactionRepository;
import com.training.pbpay.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userRepository;
	private final BeneficiaryRepository beneficiaryRepository;
	private final TransactionRepository transactionRepository;
	private final AccountMapper accountMapper;
	private final BeneficiaryMapper beneficiaryMapper;
	private final TransactionMapper transactionMapper;

	@Override
	public String registerAccount(AccountRegisterDto accountRegisterDto) {
		log.info("Entering into AccountServicceImpl register account method: ");
		User user = userRepository.findById(accountRegisterDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException(AppConstant.USER_NOT_FOUND));
		Long accountNo = accountRepository.save(accountMapper.map(accountRegisterDto, user)).getAccountNumber();
		return AppConstant.ACCOUNT_REGISTER_SUCCESS +accountNo;
	}

	@Override
	public String registerBeneficiary(BeneficiaryRegisterDto beneficiaryRegisterDto) {
		log.info("Entering into AccountServicceImpl register benficiary method: ");
		Account account = accountRepository.findById(beneficiaryRegisterDto.getAccountNumber())
				.orElseThrow(() -> new AccountNotFoundException(AppConstant.ACCOUNT_NOT_FOUND));
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findByBeneficiaryAccountNoAndAccountAccountNumber(
				beneficiaryRegisterDto.getBeneficiaryAccountNo(), account.getAccountNumber());
		
		if (beneficiary.isPresent())
			throw new BeneficiaryAlreadyExistException(
					AppConstant.BENEFICIARY_ALREADY_EXIST + beneficiaryRegisterDto.getBeneficiaryAccountNo());
		beneficiaryRepository.save(beneficiaryMapper.map(beneficiaryRegisterDto, account));
		return AppConstant.ACCOUNT_BENEFICIARY_SUCCESS;
	}

	@Override
	@Transactional
	public String transferCurrency(TransactionRequestDto transactionRequestDto)
			throws UserNotFoundException, AccountNotFoundException {
		
		log.info("Entering into transferCurrency() of AccountServiceImpl");
		
		userRepository.findById(transactionRequestDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException(AppConstant.USER_NOT_FOUND));

		Account account = accountRepository.findById(transactionRequestDto.getAccountNumber())
				.orElseThrow(() -> new AccountNotFoundException(AppConstant.ACCOUNT_NOT_FOUND));

		Beneficiary beneficiaryAccount= beneficiaryRepository.findByBeneficiaryAccountNoAndAccountAccountNumber(
				transactionRequestDto.getBeneficiaryAccountNumber(), account.getAccountNumber())
				.orElseThrow(()->new BeneficiaryNotFoundException("Beneficiary Account Not found"));
		
		if(transactionRequestDto.getTransactionAmount()>beneficiaryAccount.getTransferLimit())
			throw new TransactionLimitExceedsException("Transaction Limit Exceeds");
		
		if(transactionRequestDto.getTransactionAmount()>account.getBalance())
			throw new InsufficientBalanceException("Insufficient Balance");
		
		Transaction debitTransaction=transactionMapper.mapToDebit(transactionRequestDto, beneficiaryAccount, account);
		transactionRepository.save(debitTransaction);
		
		Transaction creditTransaction=transactionMapper.mapToCredit(transactionRequestDto, account, beneficiaryAccount);
		transactionRepository.save(creditTransaction);
		
		account.setBalance(account.getBalance()-transactionRequestDto.getTransactionAmount());
		accountRepository.save(account);
		
		return AppConstant.SUCCESS_MESSAGE;
	}

}
