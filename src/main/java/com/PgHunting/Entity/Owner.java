package com.PgHunting.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    @Email
    String email;

    @Column(nullable = false)
    long mobileNo;

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL,orphanRemoval = true)
    List<Property> properties;
}
