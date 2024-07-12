package com.cheta.bank.dto.response;

import java.time.LocalDate;

import com.cheta.bank.dto.request.UserRequestDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

// set all the annotations for getter/setter and constructors with/without args.
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

// when we send to website it will show only those parameters whose value is not null
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    public Integer id;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Character gender;
    private LocalDate dateOfBirth;
    private String aadhaarNumber;
    private String cin;

    public UserRequestDto getUserRequestDto() {
        return UserRequestDto.builder()
                .id(this.id)
                .username(this.username)
                .firstName(this.firstName)
                .middleName(this.middleName)
                .lastName(this.lastName)
                .mobileNumber(this.mobileNumber)
                .email(this.email)
                .gender(this.gender)
                .dateOfBirth(this.dateOfBirth)
                .aadhaarNumber(this.aadhaarNumber)
                .cin(this.cin)
                .build();
    }
}