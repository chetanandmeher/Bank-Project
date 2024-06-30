package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Integer> {
}
