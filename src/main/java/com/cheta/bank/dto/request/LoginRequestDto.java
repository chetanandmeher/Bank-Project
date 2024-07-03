package com.cheta.bank.dto.request;

import lombok.*;

// set all the annotations for getter/setter and constructors with/without args.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRequestDto {
    private String username;
    private String password;
}
