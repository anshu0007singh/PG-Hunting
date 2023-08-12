package com.PgHunting.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "property_Categories")
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String property;

}
