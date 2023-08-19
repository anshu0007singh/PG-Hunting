package com.PgHunting.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double rating;

    @Column(nullable = false)
    double price;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String State;

    @Column(nullable = false)
    long pinCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    Owner owner;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "property_propertyCategories",joinColumns = @JoinColumn(name = "Property_Id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PropertyType_id",referencedColumnName = "id"))
    @JsonIgnore
    Set<PropertyType> property_Type;


}
