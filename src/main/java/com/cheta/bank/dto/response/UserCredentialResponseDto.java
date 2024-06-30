package com.cheta.bank.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

// set all the annotations for getter/setter and constructors with/without args.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// when we send to website it will show only those parameters whose value is not null
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCredentialResponseDto {
    private Integer id;
    private Integer userId;
    private String username;
    private String password;
    private String passwordSalt;
    private String userRole;
    private LocalDateTime loginDateTime;
}
