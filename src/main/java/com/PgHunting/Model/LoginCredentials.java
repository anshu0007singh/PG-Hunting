package com.PgHunting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "LoginCredentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "login_person_roles",joinColumns = @JoinColumn(name = "LoginCredentials_Id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    @JsonIgnore
    Set<Role> roles;

}
