package com.cheta.bank;

import com.cheta.bank.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Password {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    public void PasswordSaving(String username, String password) {
        // TODO: Save the password securely

    }
}
