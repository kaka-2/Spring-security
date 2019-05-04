package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.OAuth2RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2RefreshTokenRepository extends JpaRepository<OAuth2RefreshToken,String> {
}
