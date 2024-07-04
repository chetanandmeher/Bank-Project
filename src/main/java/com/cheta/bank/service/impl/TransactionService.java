package com.cheta.bank.service.impl;

import com.cheta.bank.dto.response.TransactionResponseDto;
import com.cheta.bank.mysql.model.Transaction;
import com.cheta.bank.repository.TransactionRepository;
import com.cheta.bank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponseDto> getAllTransactionsByUserId(Integer userId) {
        // Implementation to retrieve all transactions for the given user
        List<Transaction> transactionList = transactionRepository.findAllByUserId(userId);
        if (!transactionList.isEmpty()) {
            return transactionList.stream().map(this::convertTransactionToTransactionResponseDto).toList();
        }
        return null;
    }

    // convert transaction sql model to transactionResponseDto model
    private TransactionResponseDto convertTransactionToTransactionResponseDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .transactionId(transaction.getTransactionId())
                .userId(transaction.getUserId())
                .accountNumber(transaction.getAccountNumber())
                .remark(transaction.getRemark())
                .dateTime(transaction.getDateTime())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .build();
    }

}
