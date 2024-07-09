package com.cheta.bank.service;

import com.cheta.bank.dto.response.AccountResponseDto;

import java.util.List;

public interface IAccountService {
    public AccountResponseDto getAccountByUserId(Integer userId);
    public List<AccountResponseDto> getAll();

    // get all the account associated with a given Userid:
    public List<AccountResponseDto> getAllByUserId(Integer userId);

    // get all accounts by user role
    public List<AccountResponseDto> getAllByUserRole(String userRole);


}
