package com.devender.emailotpverification.payload;

import lombok.Data;

@Data
public class ResponseDto {
    private Long userId;
    private String username;
    private String email;
    private boolean verified;
    private String message;
}
