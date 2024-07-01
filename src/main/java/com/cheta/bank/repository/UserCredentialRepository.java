package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.UserCredential;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialRepository extends CrudRepository<UserCredential, Integer> {
    public UserCredential getUserCredentialByUserId(Integer userId);

    public UserCredential findByUsername(String username);
    public UserCredential findByUserId(Integer userId);
}
