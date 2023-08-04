package com.PgHunting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double rating;
    double price;
    String address;
    String city;
    String State;
    long pinCode;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "property_propertyType",joinColumns = @JoinColumn(name = "Property_Id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PropertyType_id",referencedColumnName = "id"))
    @JsonIgnore
    Set<Role> roles;


}
