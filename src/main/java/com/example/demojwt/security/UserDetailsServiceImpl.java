package com.example.demojwt.security;

import com.example.demojwt.Applicationuser.ApplicationUser;
import com.example.demojwt.Applicationuser.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findApplicationUserByUsername(username).get();

        if (user == null ) {
            throw new UsernameNotFoundException("User with username " + username + "not found");
        }

        return new UserDetailsImpl(user);
    }
}
