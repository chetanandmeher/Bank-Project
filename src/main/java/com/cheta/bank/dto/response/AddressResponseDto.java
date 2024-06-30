package com.cheta.bank.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponseDto {
    private String address;
    private String pincode;
    private String city;
    private String district;
    private String state;

}
