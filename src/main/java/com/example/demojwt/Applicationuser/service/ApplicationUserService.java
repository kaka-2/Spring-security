package com.example.demojwt.Applicationuser.service;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.domain.Role;
import com.example.demojwt.Applicationuser.model.RegisterUser;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Applicationuser.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUser addUsers(RegisterUser user){
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role adminUser = roleRepository.findByName("ROLE_ADMIN");

        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");

        ApplicationUser user1 = new ApplicationUser();
        user1.setUsername(user.getUsername());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setRoles(Arrays.asList(roleUser));
        user1.setEnabled(true);
        return userRepository.save(user1);
    }

    public List<ApplicationUser> getUsers(){
        return userRepository.findAll();
    }

    public Optional<ApplicationUser> getUser(Long userId){
        return userRepository.findById(userId);
    }

    @Transactional
    public Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);

        if (role == null){
            role = new Role(name);
            roleRepository.save(role);
        }

        return role;
    }
}
