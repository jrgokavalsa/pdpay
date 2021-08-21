package com.training.pbpay.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.pbpay.constant.AppConstant;
import com.training.pbpay.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("VALIDATION_FAILED");
		List<String> details = ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"VALIDATION_FAILED", details);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public final ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(UserAlreadyExistException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				AppConstant.USER_ALREADY_EXIST, details);
		log.error(errors.toString());
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.CONFLICT);

	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				AppConstant.USER_NOT_FOUND, details);
		log.error(errors.toString());
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				AppConstant.ACCOUNT_NOT_FOUND, details);
		log.error(errors.toString());
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BeneficiaryAlreadyExistException.class)
	public final ResponseEntity<ErrorResponse> handleBeneficiaryAlreadyExistException(BeneficiaryAlreadyExistException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				AppConstant.BENEFICIARY_ALREADY_EXIST, details);
		log.error(errors.toString());
		return new ResponseEntity<ErrorResponse>(errors, HttpStatus.CONFLICT);

	}
}
