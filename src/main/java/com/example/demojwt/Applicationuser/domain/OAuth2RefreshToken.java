package com.example.demojwt.Applicationuser.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "oauth_refresh_token")
public class OAuth2RefreshToken {

    @Id
    @Column(name = "token_id")
    public String tokenId;

    @Column(name = "token", length = 4000)
    public String token;

    @Column(name = "authentication",length = 4000)
    public String authentication;

}
