package com.PgHunting.util;

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
public class PropertyRequestDto {

    @NotBlank(message = "price cannot be blank")
    double price;

    @NotBlank(message = "Description cannot be null")
    String description;

    @NotBlank(message = "Mobile number cannot be null")
    String mobileNumber;

    @NotBlank(message = "Address cannot be blank")
    String address;

    @NotBlank(message = "City cannot be blank")
    String city;

    @NotBlank(message = "state cannot be blank")
    String State;

    @NotBlank(message = "Pin code cannot be blank")
    long pinCode;

    @NotBlank(message = "Property type cannot be null")
    List<String> type;

}
