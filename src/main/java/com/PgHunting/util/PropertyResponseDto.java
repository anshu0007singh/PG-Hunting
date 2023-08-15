package com.PgHunting.util;

import com.PgHunting.Model.Owner;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponseDto {

    double rating;

    double price;

    String address;

    String city;

    String State;

    long pinCode;

    List<String> type;

    Owner owner;
}
