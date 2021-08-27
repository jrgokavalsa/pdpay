package com.training.pbpay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.pbpay.dto.TransactionRequestDto;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.Beneficiary;
import com.training.pbpay.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
	
	@Mapping(target = "transactionDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "status", expression = "java(com.training.pbpay.constant.AppConstant.TRANSACTION_STATUS_SUCCESS)")
	@Mapping(target = "beneficiaryAccountNumber", source = "beneficiaryAccount")
	@Mapping(target = "transactionId", ignore = true)
	@Mapping(target = "transactionType", expression = "java(com.training.pbpay.constant.AppConstant.DEBIT)")
	@Mapping(target = "sourceAccountNumber", source = "beneficiaryAccount.account")
	Transaction mapToDebit(TransactionRequestDto transactionRequestDto, Beneficiary beneficiaryAccount,
			Account sourceAccount);

	@Mapping(target = "transactionDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "status", expression = "java(com.training.pbpay.constant.AppConstant.TRANSACTION_STATUS_SUCCESS)")
	@Mapping(target = "beneficiaryAccountNumber", source = "beneficiaryAccount")
	@Mapping(target = "transactionId", ignore = true)
	@Mapping(target = "transactionType", expression = "java(com.training.pbpay.constant.AppConstant.CREDIT)")
	@Mapping(target = "sourceAccountNumber", source = "beneficiaryAccount.account")
	Transaction mapToCredit(TransactionRequestDto transactionRequestDto, 
			Account sourceAccount,Beneficiary beneficiaryAccount);
}
