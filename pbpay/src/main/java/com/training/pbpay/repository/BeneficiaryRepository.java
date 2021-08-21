package com.training.pbpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.pbpay.model.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{

	Optional<Beneficiary> findByBeneficiaryAccountNoAndAccountAccountNumber(long beneficiaryAccountNo, long account);

}
