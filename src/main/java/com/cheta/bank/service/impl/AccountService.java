package com.cheta.bank.service.impl;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.mysql.model.Account;
import com.cheta.bank.repository.AccountRepository;
import com.cheta.bank.repository.BranchRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public AccountResponseDto getAccountByUserId(Integer userId) {
        Optional<Account> account = accountRepository.findById(userId);
        // convert account mysql model to accountResponseDto
        return convertAccountToAccountResponseDto(account.get());
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        if (!accountList.isEmpty()) {
            return accountList.stream().map(this::convertAccountToAccountResponseDto).toList();
        } else {
            return null;
        }
    }

    @Override
    public List<AccountResponseDto> getAllAccountsByUserId(Integer userId) {
        List<Account> accountList = accountRepository.findAllByUserId(userId);
        if (!accountList.isEmpty()) {
            return accountList.stream().map(this::convertAccountToAccountResponseDto).toList();
        }
        return null;
    }

    // Get all accounts with a given user role
    @Override
    public List<AccountResponseDto> getAllByUserRole(String userRole) {
//        List<UserCredentialResponseDto> userCredentialResponseDtoList = userCredentialService.getAllByUserRole("Customer");
//        // get teh userid from each userCredentials
//        List<Integer> customerUserIdlist = userCredentialResponseDtoList.stream()
//                .map(UserCredentialResponseDto::getUserId)
//                .toList();
//        // get the users with userId in the userIdList
//        List<AccountResponseDto> accountResponseDtoList = customerUserIdlist.stream()
//                .map(accountRepository::findAll)
//                .toList();
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        return accountList.stream().map(this::convertAccountToAccountResponseDto).toList();
    }





    private AccountResponseDto convertAccountToAccountResponseDto(Account account) {
            return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .type(account.getAccountType())
                .balance(account.getBalance())
                .rateOfInterest(account.getRateOfInterest())
                .branchName(branchRepository.findById(account.getBranchId()).get().getName())
                .customerUsername(userCredentialRepository.findByUserId(account.getUserId()).getUsername())
                .openingDate(account.getOpeningDate())
                .build();
    }
}
