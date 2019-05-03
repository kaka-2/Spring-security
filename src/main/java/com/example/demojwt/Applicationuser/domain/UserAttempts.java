package com.example.demojwt.Applicationuser.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class UserAttempts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String username;

    public String attempts;
    public Date lastModified;
}
