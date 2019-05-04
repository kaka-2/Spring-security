package com.example.demojwt.Applicationuser.service;

import com.example.demojwt.Applicationuser.domain.UserAttempts;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Applicationuser.repository.UserAttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserAttemptService {

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private UserAttemptsRepository userAttemptsRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public void updateFailAttempts(String username) {
        UserAttempts userAttempts =userAttemptsRepository.findUserAttemptsByUsername(username);

        if (userAttempts == null) {

            UserAttempts user = new UserAttempts();
            user.setAttempts(1);
            user.setLastModified(new Date(System.currentTimeMillis()));
            userAttemptsRepository.save(user);
        }else {
            userAttempts.setAttempts(userAttempts.getAttempts() + 1);
            userAttemptsRepository.save(userAttempts);

            if (userAttempts.getAttempts() + 1 >= MAX_ATTEMPTS ) {
                
            }

        }
    }

    public void resetFailAttempts(String username)throws UsernameNotFoundException {
        UserAttempts userAttempts =userAttemptsRepository.findUserAttemptsByUsername(username);
        if (userAttempts != null ) {
            userAttempts.setAttempts(0);
            userAttemptsRepository.save(userAttempts);
        }
        throw new UsernameNotFoundException(username);
    }
}
