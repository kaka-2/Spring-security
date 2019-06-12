package com.example.demojwt.Mpesa;

import lombok.Data;

@Data
public class MpesaAuthorizationResponse {
    private String access_token;
    private String expires_in;
}
