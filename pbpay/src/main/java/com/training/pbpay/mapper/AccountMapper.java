package com.training.pbpay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.pbpay.dto.AccountRegisterDto;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.User;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(target = "accountNumber", ignore = true)
	@Mapping(target = "user", source = "user")
	Account map(AccountRegisterDto accountRegisterDto,User user);
}
