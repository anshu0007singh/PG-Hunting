package com.PgHunting.util;

import com.PgHunting.Model.Owner;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
