package com.example.demojwt.Utils;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.domain.OAuth2Client;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Applicationuser.domain.Role;
import com.example.demojwt.Applicationuser.repository.OAuth2ClientRepository;
import com.example.demojwt.Applicationuser.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class IntialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetUp = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetUp)
            return;


        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        ApplicationUser user = new ApplicationUser();
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setUsername("john");
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);


        userRepository.save(user);


        //set oauth2 client
        OAuth2Client oAuth2Client = new OAuth2Client();
        oAuth2Client.setClientId("tracom");
        oAuth2Client.setClientSecret(bCryptPasswordEncoder.encode("password"));
        oAuth2Client.setScope("all");
        oAuth2Client.setGrantTypes("password,refresh_token,client_credentials");
        oAuth2Client.setAccessTokenValidity(300);
        oAuth2Client.setAuthorities("ROLE_ADMIN");
        oAuth2Client.setRefreshTokenValidity(60 * 60 * 10);

        oAuth2ClientRepository.save(oAuth2Client);

        alreadySetUp = true;
    }

    @Transactional
    public Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);

        if (role == null ) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

}
