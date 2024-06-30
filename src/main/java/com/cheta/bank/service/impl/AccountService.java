package com.cheta.bank.service.impl;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.mysql.model.Account;
import com.cheta.bank.repository.AccountRepository;
import com.cheta.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    // get the account by userId
    public AccountResponseDto getAccountByUserId(Integer userId) {
        Optional<Account> account = accountRepository.findById(userId);
        // convert account mysql model to accountResponseDto
        return convertAccountToAccountRepsonDto(account.get());
    }

    private AccountResponseDto convertAccountToAccountRepsonDto(Account account) {
        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .type(account.getAccountType())
                .balance(account.getBalance())
                .rateOfInterest(account.getRateOfInterest())
                .branchId(account.getBranchId())
                .build();
    }
}
