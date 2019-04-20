package com.example.demojwt.Applicationuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser,Long> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);

}
