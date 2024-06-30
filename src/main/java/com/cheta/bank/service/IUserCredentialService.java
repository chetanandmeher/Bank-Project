package com.cheta.bank.service;

import com.cheta.bank.mysql.model.UserCredential;

public interface IUserCredentialService {
    public UserCredential getUserCredentialByUserId(Integer id);
}
