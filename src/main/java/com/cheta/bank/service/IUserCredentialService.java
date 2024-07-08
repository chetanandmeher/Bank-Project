package com.cheta.bank.service;

import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.mysql.model.UserCredential;

import java.util.List;

public interface IUserCredentialService {
    public UserCredential getUserCredentialByUserId(Integer id);
    public List<UserCredentialResponseDto> getAllByUserRole(String userRole);

}
