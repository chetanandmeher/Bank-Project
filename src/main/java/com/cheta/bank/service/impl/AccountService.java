package com.cheta.bank.service.impl;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.mysql.model.Account;
import com.cheta.bank.repository.AccountRepository;
import com.cheta.bank.repository.BranchRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    BranchRepository branchRepository;

    // get the account by userId
    public AccountResponseDto getAccountByUserId(Integer userId) {
        Optional<Account> account = accountRepository.findById(userId);
        // convert account mysql model to accountResponseDto
        return convertAccountToAccountRepsonDto(account.get());
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        if (!accountList.isEmpty()) {
            return accountList.stream().map(this::convertAccountToAccountRepsonDto).toList();
        } else {
            return null;
        }
    }

    private AccountResponseDto convertAccountToAccountRepsonDto(Account account) {
        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .type(account.getAccountType())
                .balance(account.getBalance())
                .rateOfInterest(account.getRateOfInterest())
                .branchName(branchRepository.findById(account.getBranchId()).get().getName())
                .customerUsername(userCredentialRepository.findByUserId(account.getUserId()).getUsername())
                .build();
    }
}
