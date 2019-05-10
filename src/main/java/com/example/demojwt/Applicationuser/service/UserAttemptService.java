package com.example.demojwt.Applicationuser.service;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.domain.UserAttempt;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Applicationuser.repository.UserAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserAttemptService {

    @Autowired
    private UserAttemptRepository userAttemptRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public void updateFailAttempts(String username ) {
        Optional<UserAttempt> opt = userAttemptRepository.findUserAttemptsByUsername(username);

        if (opt.isPresent()) {
            UserAttempt user = opt.get();
            user.setAttempts(user.getAttempts() - 1);
            user.setLastModified(new Date(System.currentTimeMillis()));
            userAttemptRepository.save(user);

            if (user.getAttempts() <= 0 ) {
                ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(username).get();
                applicationUser.setAccountNonLocked(false);
                user.setAttempts(0);
                userAttemptRepository.save(user);
                applicationUserRepository.save(applicationUser);
            }
        }else {
            UserAttempt user = new UserAttempt();
            user.setUsername(username);
            user.setAttempts(3);
            user.setLastModified(new Date(System.currentTimeMillis()));

            userAttemptRepository.save(user);
        }
    }

    public void resetFailAttempts(String username ) {
        Optional<UserAttempt> opt = userAttemptRepository.findUserAttemptsByUsername(username);

        if (opt.isPresent()) {
            UserAttempt user = opt.get();

            user.setAttempts(3);
            user.setLastModified(new Date(System.currentTimeMillis()));
            userAttemptRepository.save(user);
        }

    }

    public int getApplicationUserAttempts(String username) {
        Optional<UserAttempt> opt = userAttemptRepository.findUserAttemptsByUsername(username);

        if (opt.isPresent()) {
            UserAttempt user = opt.get();

            return user.getAttempts();
        }

        return -1;
    }
}
