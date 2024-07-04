package com.cheta.bank.controller;

import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserCredentialRepository userCredentialRepository;




}
