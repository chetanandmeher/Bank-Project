package com.cheta.bank.service;

import com.cheta.bank.dto.response.AccountResponseDto;

import java.util.List;

public interface IAccountService {
    public AccountResponseDto getAccountByUserId(Integer userId);
    public List<AccountResponseDto> getAllAccounts();

    // get all the account associated with a given Userid:
    public List<AccountResponseDto> getAllAccountsByUserId(Integer userId);


}
