package com.example.demojwt.Utils;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Applicationuser.domain.Privilege;
import com.example.demojwt.Applicationuser.repository.PrivilegeRepository;
import com.example.demojwt.Applicationuser.domain.Role;
import com.example.demojwt.Applicationuser.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class IntialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetUp = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetUp)
            return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPriviliges = Arrays.asList(readPrivilege,writePrivilege);

        createRoleIfNotFound("ROLE_ADMIN",adminPriviliges);
        createRoleIfNotFound("ROLE_USER",Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        ApplicationUser user = new ApplicationUser();
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setUsername("john");
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetUp = true;
    }

    @Transactional
    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);

        if (role == null ) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null ) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }

        return privilege;
    }
}
