package com.cheta.bank.service;

import com.cheta.bank.dto.response.TransactionResponseDto;

import java.util.List;

public interface ITransactionService {
    public List<TransactionResponseDto> getAllTransactionsByUserId(Integer userId);
    public List<TransactionResponseDto> getAllByUserRole(String userRole);

}
