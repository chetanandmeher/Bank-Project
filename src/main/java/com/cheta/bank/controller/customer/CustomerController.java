package com.cheta.bank.controller.customer;


import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.dto.response.TransactionResponseDto;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;


    // dashboard for customer
    @GetMapping("/customers/dashboard")
    public String getCustomerDashboard(Model model, HttpSession session) {

        // get loginRequestDto from session attribute.
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");
        model.addAttribute("loginRequestDto", loginRequestDto);
        return "/customer/dashboard";
    }

    //account table for customer
    @GetMapping("/customers/accounts")
    public String getCustomerAccounts(Model model, HttpSession session) {

        // get loginRequestDto from session attribute.
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");
        model.addAttribute("loginRequestDto", loginRequestDto);

        // Retrieve customer's accounts and display them in a table
        // All accounts
        Integer userId = userCredentialRepository.findByUsername(loginRequestDto.getUsername()).getUserId();

        // get all accounts and add it to the model
        List<AccountResponseDto> accountResponseDtoList = accountService.getAllAccountsByUserId(userId);
        model.addAttribute("accounts", accountResponseDtoList);

        return "/customer/accounts-table";
    }

    @GetMapping("/customers/transactions")
    public String getCustomerTransactions(Model model, HttpSession session) {
        // get loginRequestDto from session attribute.
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");
        model.addAttribute("loginRequestDto", loginRequestDto);

        // Get all transactions for the current user and display them in a table
        Integer userId = userCredentialRepository.findByUsername(loginRequestDto.getUsername()).getUserId();

        // Get all transactions for the current user and add it to the model
        LocalDate today = LocalDate.now();
        List<TransactionResponseDto> transactionResposnelDtoList = transactionService.getAllTransactionsByUserId(userId);
        model.addAttribute("transactions", transactionResposnelDtoList);

        TransactionResponseDto transactionResponseDto1 = TransactionResponseDto.builder()
                .id(1)
                .transactionId(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                .userId(1)
                .accountNumber(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                .remark("hotel")
                .dateTime(LocalDateTime.now())
                .type("Debit")
                .status("Successful")
                .amount(1200.00)
                .build();
        TransactionResponseDto transactionResponseDto2 = TransactionResponseDto.builder()
                .id(2)
                .transactionId(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                .userId(1)
                .accountNumber(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                .remark("hotel")
                .dateTime(LocalDateTime.now())
                .type("Credit")
                .status("Successful")
                .amount(1200.00)
                .build();
        model.addAttribute("fakeTransactions", fakeTransactions());

        return "/customer/transactions-table";

    }

    private List<TransactionResponseDto> fakeTransactions() {
        List<TransactionResponseDto> fakeTransactionsList = new ArrayList<TransactionResponseDto>();
        for(int i = 0; i <= 50;i++) {
            TransactionResponseDto transactionResponseDto = TransactionResponseDto.builder()
                   .id(i)
                   .transactionId(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                   .userId(1)
                   .accountNumber(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                   .remark("hotel")
                   .dateTime(LocalDateTime.now())
                   .type(Arrays.asList("Debit","Credit","Deposit").get(new Random().nextInt(3)))
                   .status(Arrays.asList("Successful","Pending","Failed").get(new Random().nextInt(3)))
                   .amount(ThreadLocalRandom.current().nextDouble(1000, Double.MAX_VALUE))
                   .build();
            fakeTransactionsList.add(transactionResponseDto);
        }
        return fakeTransactionsList;
    }
}
