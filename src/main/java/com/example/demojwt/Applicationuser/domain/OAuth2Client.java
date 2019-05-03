package com.example.demojwt.Applicationuser.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "oauth_client_details")
public class OAuth2Client{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "client_id", nullable = false)
    public String clientId;
    @Column(name = "resource_ids")
    public String resourceIds;
    @Column(name = "client_secret", nullable = false)
    public String clientSecret;
    @Column(name = "scope", nullable = false)
    public String scope;
    @Column(name = "authorized_grant_types", nullable = false)
    public String grantTypes;
    @Column(name = "web_server_redirect_uri")
    public String webServerRedirectUri;
    @Column(name = "authorities")
    public String authorities;
    @Column(name = "access_token_validity", nullable = false)
    public Integer accessTokenValidity;
    @Column(name = "refresh_token_validity")
    public int refreshTokenValidity;
    @Column(name = "additional_information", length = 4096)
    public String additionalInfo;
    @Column(name = "autoapprove")
    public String autoApprove;

}