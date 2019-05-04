package com.example.demojwt.Applicationuser.service;

import com.example.demojwt.Applicationuser.domain.UserAttempts;
import com.example.demojwt.Applicationuser.repository.UserAttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAttemptService {

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    public void updateFailAttempts(String username) {
        UserAttempts userAttempts =userAttemptsRepository.findUserAttemptsByUsername(username);

        if (userAttempts == null) {
            userAttemptsRepository.save()
        }
    }
}
