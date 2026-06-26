package com.cheta.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCredentialDto {

    private Integer id;
    private Integer userId;
    private String username;
    private String password;
    private String passwordSalt;
    private String userRole;
    private LocalDateTime loginDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private Integer createdBy;

}
