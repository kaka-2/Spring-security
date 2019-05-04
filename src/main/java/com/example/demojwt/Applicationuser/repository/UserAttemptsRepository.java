package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.UserAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAttemptsRepository extends JpaRepository<UserAttempts,Long> {
    UserAttempts findUserAttemptsByUsername(String username);
}
