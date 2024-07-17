package com.cheta.bank.service;

import com.cheta.bank.dto.AccountDto;
import com.cheta.bank.dto.ListAccountDto;
import com.cheta.bank.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService<T,K> {
    //* User operations here
    public List<UserResponseDto> getAllUsers();

    public T getUserByUsername(String username);

    public UserResponseDto getByUserId(Integer userId);

    public T updateUserDetails(K userDto);



    //* Account related methods
    public ListAccountDto updateUserAccounts(ListAccountDto listAccountDto);
    public List<AccountDto> getAllAccountsByUserId(Integer userId);



}