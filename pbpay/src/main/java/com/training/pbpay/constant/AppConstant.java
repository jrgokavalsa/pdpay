package com.training.pbpay.constant;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppConstant {

	public static final String FAILURE_MESSAGE = "FAILURE";
	public static final String SUCCESS_MESSAGE = "SUCCESS";
	public static final String NO_RECORDS_FOUND = "No Records Found";
	public static final String USER_NOT_FOUND = "User Not Found";
	public static final String USER_ALREADY_EXIST = "User Already Exist ";
	public static final String AUTHENTICATION_SUCCESSFUL = "Authentication Successful";
	public static final String AUTHENTICATION_FAILED = "Authentication Failed";

	public static final String CREDIT = "CREDIT";
	public static final String DEBIT = "DEBIT";

	public static final String TRANSACTION_STATUS_SUCCESS = "Transaction Successfully Completed";

	public static final Integer AUTHENTICATION_SUCCESSFUL_CODE = HttpStatus.OK.value();
	public static final String ACCOUNT_NOT_FOUND = "Account not found";
	public static final String ACCOUNT_REGISTER_SUCCESS = "Account Registered Successfully";
	public static final String ACCOUNT_BENEFICIARY_SUCCESS = "Beneficiary Registered Successfully";
	public static final String BENEFICIARY_NOT_FOUND = "Beneficiary Account Not Found";
	public static final String BENEFICIARY_ALREADY_EXIST = "Beneficiary Already Exist with AccountNo: ";
	
}
