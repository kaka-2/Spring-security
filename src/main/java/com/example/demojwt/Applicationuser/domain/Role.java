package com.example.demojwt.Applicationuser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<ApplicationUser> users;


    public Role(String name) {
        this.name = name;
    }
}
