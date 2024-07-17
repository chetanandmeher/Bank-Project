package com.cheta.bank.service.impl;

import com.cheta.bank.dto.AccountDto;
import com.cheta.bank.mysql.model.Account;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.AccountRepository;
import com.cheta.bank.repository.BranchRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    UserCredentialService userCredentialService;

    // get the account by userId
    public AccountDto getAccountByUserId(Integer userId) {
        Optional<Account> account = accountRepository.findById(userId);
        // convert account mysql model to accountResponseDto
        return convertAccountToAccountResponseDto(account.get());
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        if (!accountList.isEmpty()) {
            return accountList.stream().map(this::convertAccountToAccountResponseDto).toList();
        } else {
            return null;
        }
    }

    @Override
    public List<AccountDto> getAllByUserId(Integer userId) {
        List<Account> accountList = accountRepository.findAllByUserId(userId);
        if (!accountList.isEmpty()) {
            return accountList.stream().map(this::convertAccountToAccountResponseDto).toList();
        }
        return null;
    }

    // Get all accounts with a given user role
    @Override
    public List<AccountDto> getAllByUserRole(String userRole) {
        // Get all userCredential related to the userRole
        List<UserCredential> userCredentials = userCredentialRepository.findAllByUserRole(userRole);
        // Get all the userID of each userCredential
        List<Integer> customerUserIdList = userCredentials.stream()
                .map(UserCredential::getUserId)
                .toList();
        List<AccountDto> accountsResponseDtoList = new ArrayList<>();
        customerUserIdList.forEach(userId-> {
            // Get all the accounts related to the userId
            List<Account> accountList = accountRepository.findAllByUserId(userId);
            accountsResponseDtoList.addAll(accountList.stream()
                    .map(this::convertAccountToAccountResponseDto)
                    .toList());
        });
        return accountsResponseDtoList ;
    }

    private AccountDto convertAccountToAccountResponseDto(Account account) {
            return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .type(account.getAccountType())
                .balance(account.getBalance())
                .rateOfInterest(account.getRateOfInterest())
                .branch(branchRepository.findById(account.getBranchId()).get().getName())
                .customerUsername(userCredentialRepository.findByUserId(account.getUserId()).getUsername())
                .openingDate(account.getOpeningDate())
                .userId(account.getUserId())
                .build();
    }
}
