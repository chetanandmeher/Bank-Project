package com.cheta.bank.service;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.mysql.model.Account;

public interface IAccountService {
    public AccountResponseDto getAccountByUserId(Integer userId);

}
