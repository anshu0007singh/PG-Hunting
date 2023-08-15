package com.PgHunting.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false,unique = true)
    String username;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    @Min(value = 1000000000, message = "Mobile no. should not be less 10 digits")
    @Max(value = 9999999999L,message = "mobile no. should not be more than 10 digits")
    long mobileNo;
}
