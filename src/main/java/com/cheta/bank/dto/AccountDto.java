package com.cheta.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Integer id;
    private String accountNumber;
    private String customerUsername;
    private String type;
    private Double balance;
    private Float rateOfInterest;
    private String branch;
    private LocalDate openingDate;
    private Integer userId;
}
