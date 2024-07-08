package com.cheta.bank.service;

import com.cheta.bank.dto.response.AddressResponseDto;
import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {
    // User operations here
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto getUserByUsername(String username);
    public UserResponseDto getByUserId(Integer userId);

}
