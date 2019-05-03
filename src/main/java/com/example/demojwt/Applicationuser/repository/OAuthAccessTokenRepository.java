package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.OAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthAccessTokenRepository extends JpaRepository<OAuthAccessToken,Long> {
}
