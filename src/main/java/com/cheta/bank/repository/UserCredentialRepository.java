package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.UserCredential;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCredentialRepository extends CrudRepository<UserCredential, Integer> {
    public UserCredential getUserCredentialByUserId(Integer userId);
    public UserCredential findByUsername(String username);
    public UserCredential findByUserId(Integer userId);
    public List<UserCredential> findAllByUserRole(String userRole);
}
