package com.example.demojwt.Applicationuser.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "oauth_access_token")
public class OAuthAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "token_id")
    public String tokenId;

    @Column(name = "token", length = 4000)
    public String token;

    @Column(name = "authentication_id")
    public String authenticationId;

    @Column(name = "user_name")
    public String username;

    @Column(name = "client_id")
    public String clientId;

    @Column(name = "authentication",length = 4000)
    public String authentication;

    @Column(name = "refresh_token")
    public String refreshToken;
}
