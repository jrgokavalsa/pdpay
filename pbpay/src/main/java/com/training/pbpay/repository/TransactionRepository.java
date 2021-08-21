package com.training.pbpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.pbpay.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
