package com.PgHunting.util;

import com.PgHunting.Entity.Owner;
import jakarta.validation.constraints.NotBlank;
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

    String description;

    String mobileNumber;

    String address;

    String city;

    String State;

    long pinCode;

    List<String> type;

    Owner owner;
}
