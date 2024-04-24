package com.devender.emailotpverification.payload;

import lombok.Data;

@Data
public class RequestDto {
    private String username;
    private String email;

}
