package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    public List<Transaction> findAllByUserId(Integer userId);


}
