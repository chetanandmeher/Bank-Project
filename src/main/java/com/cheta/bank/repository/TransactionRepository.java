package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
