package com.cheta.bank.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDto {
    private String accountNumber;
    private String type;
    private Double balance;
    private Float rateOfInterest;
    private Integer branchId;
    private LocalDate openingDate;
}
