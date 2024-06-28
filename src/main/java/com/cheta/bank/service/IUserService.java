package com.cheta.bank;

import com.cheta.bank.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {
    // User operations here
    public List<UserResponseDto> getAllUsers();

}
