package com.cheta.bank.service;

import com.cheta.bank.dto.UserDto;
import com.cheta.bank.dto.response.UserResponseDto;

public interface IAdminService {
    // Admin-specific methods go here.
    public UserDto getUserByUsername(String username);
    public UserResponseDto updateUserDetails(UserResponseDto userResponseDto);
}
