package com.cheta.bank.controller;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts-table")
    public String accountsTable(Model model) {
        List<AccountResponseDto> accountResponseDtoList = accountService.getAllAccounts();
        model.addAttribute("accounts", accountResponseDtoList);
        return "accounts-table";
    }


}
