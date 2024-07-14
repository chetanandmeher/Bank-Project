package com.cheta.bank.service;

import com.cheta.bank.dto.AccountDto;

import java.util.List;

public interface IAccountService {
    public AccountDto getAccountByUserId(Integer userId);
    public List<AccountDto> getAll();

    // get all the account associated with a given Userid:
    public List<AccountDto> getAllByUserId(Integer userId);

    // get all accounts by user role
    public List<AccountDto> getAllByUserRole(String userRole);


}
