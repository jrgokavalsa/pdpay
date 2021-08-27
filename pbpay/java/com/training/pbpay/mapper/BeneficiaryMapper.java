package com.training.pbpay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.pbpay.dto.BeneficiaryRegisterDto;
import com.training.pbpay.model.Account;
import com.training.pbpay.model.Beneficiary;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper {
	@Mapping(target = "beneficiaryId", ignore = true)
	@Mapping(target ="ifsCode",source = "beneficiaryRegisterDto.ifsCode")
	Beneficiary map(BeneficiaryRegisterDto beneficiaryRegisterDto,Account account);
}
