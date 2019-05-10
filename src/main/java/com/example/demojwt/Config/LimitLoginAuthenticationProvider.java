package com.example.demojwt.Config;

import com.example.demojwt.Applicationuser.service.UserAttemptService;
import com.example.demojwt.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(LimitLoginAuthenticationProvider.class);

    @Autowired
    private UserAttemptService userAttemptService;

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }


    @Autowired
    @Lazy
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        try {
            Authentication auth =  super.authenticate(authentication);
            log.info("Username " + authentication.getName() );
            userAttemptService.resetFailAttempts(authentication.getName());

            return auth;
        } catch (BadCredentialsException e) {
            userAttemptService.updateFailAttempts(authentication.getName());
            throw e;
        } catch (LockedException e ) {
            String error = "";

            int attempts = userAttemptService.getApplicationUserAttempts(authentication.getName());

            if (attempts != -1 ) {
                error = "User account is locked! maximum attempts reached";
            } else {
                error = e.getMessage();
            }
            throw new LockedException(error);
        }
    }
}
