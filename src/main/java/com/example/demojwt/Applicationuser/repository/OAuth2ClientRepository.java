package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.OAuth2Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2ClientRepository extends JpaRepository<OAuth2Client,Long> {
}
