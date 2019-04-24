package com.example.demojwt.Applicationuser.repository;

import com.example.demojwt.Applicationuser.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{

    Role findByName(String name);
}
