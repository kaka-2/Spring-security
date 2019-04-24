package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
