package com.example.demojwt.Applicationuser.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class UserAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true,nullable = false)
    public String username;
    public int attempts;

    public Date lastModified;
}
