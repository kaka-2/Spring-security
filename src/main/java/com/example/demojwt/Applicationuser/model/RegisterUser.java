package com.example.demojwt.Applicationuser.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUser {


    @Size(min = 3,message = "{username.length}")
    @NotBlank(message = "{username.notempty}")
    public String username;

    @Size(min = 8,message = "{password.length}")
    @NotBlank(message = "{password.notempty}")
    public String password;

    @NotBlank(message = "{email.notempty}")
    @Email(message = "{email.valid}")
    public String email;
}
