package com.cheta.bank.controller;

import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserCredentialRepository userCredentialRepository;




}
