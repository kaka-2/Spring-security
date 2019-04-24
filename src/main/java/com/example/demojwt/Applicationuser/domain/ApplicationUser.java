package com.example.demojwt.Applicationuser.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Data
public class ApplicationUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String username;
    public String password;
    public String email;
    public boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    public Collection<Role> roles;

}
