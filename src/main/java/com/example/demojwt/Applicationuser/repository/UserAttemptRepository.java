package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.UserAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAttemptRepository extends JpaRepository<UserAttempt,Long> {
    Optional<UserAttempt> findUserAttemptsByUsername(String username);
}
