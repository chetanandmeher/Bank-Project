package com.cheta.bank.service;

import com.cheta.bank.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService<T,K> {
    // User operations here
    public List<UserResponseDto> getAllUsers();

    public T getUserByUsername(String username);

    public UserResponseDto getByUserId(Integer userId);

    public T updateUser(K userDto);



}