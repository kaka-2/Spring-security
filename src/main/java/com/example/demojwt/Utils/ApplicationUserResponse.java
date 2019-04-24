package com.example.demojwt.Utils;

import lombok.Data;

@Data
public class ApplicationUserResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}
