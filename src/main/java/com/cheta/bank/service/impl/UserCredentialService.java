package com.cheta.bank.service.impl;


import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialService implements IUserCredentialService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    public UserCredential getUserCredentialByUserId(Integer id) {
        return null;
    }

    @Override
    public List<UserCredentialResponseDto> getAllByUserRole(String userRole) {
        List<UserCredential> userCredentials = userCredentialRepository.findAllByUserRole(userRole);
        if (userCredentials != null) {
            return userCredentials.stream()
                   .map(this::convertUserCredentialToUserCredentialResponseDto)
                   .toList();
        }
        return null;
    }

    private UserCredentialResponseDto convertUserCredentialToUserCredentialResponseDto(UserCredential userCredential) {
        return UserCredentialResponseDto.builder()
                .id(userCredential.getId())
                .userId(userCredential.getUserId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .passwordSalt(userCredential.getPasswordSalt())
                .userRole(userCredential.getUserRole())
                .loginDateTime(userCredential.getLoginDateTime())
                .build();
    }
}
