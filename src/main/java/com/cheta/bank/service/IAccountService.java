package com.cheta.bank.service;

import com.cheta.bank.dto.response.AccountResponseDto;

import java.util.List;

public interface IAccountService {
    public AccountResponseDto getAccountByUserId(Integer userId);
    public List<AccountResponseDto> getAllAccounts();

}
