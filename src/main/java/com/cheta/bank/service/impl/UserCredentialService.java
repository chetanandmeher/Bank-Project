package com.cheta.bank.service.impl;


import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService implements IUserCredentialService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    public UserCredential getUserCredentialByUserId(Integer id) {
        return null;
    }
}
