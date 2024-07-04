package com.cheta.bank.dto.response;

import ch.qos.logback.core.joran.conditional.PropertyEvalScriptBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDto {
    private Integer id;
    private String transactionId;
    private Integer userId;
    private String accountNumber;
    private String remark;
    private LocalDateTime dateTime;
    private String type;
    private String status;
    private Double amount;



}
