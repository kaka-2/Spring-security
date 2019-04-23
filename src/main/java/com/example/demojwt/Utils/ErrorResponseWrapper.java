package com.example.demojwt.Utils;

import lombok.Data;

@Data
public class ErrorResponseWrapper {
    public int statusCode;
    public String message;
    public Long timestamp;
}
