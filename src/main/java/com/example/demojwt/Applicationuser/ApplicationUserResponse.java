package com.example.demojwt.Applicationuser;

import lombok.Data;

@Data
public class ApplicationUserResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}
