package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Integer> {
    // Get all the user associated with th given userID
    public List<Account> findAllByUserId(Integer userId);
    public  Account findByUserId(Integer userId);
}
