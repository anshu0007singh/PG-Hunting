package com.PgHunting.util;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotBlank(message = "username cannot be null")
    private String userName;

    @NotBlank(message = "name cannot be null")
    private String name;

    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Mobile no. cannot be null")
    @Min(value = 1000000000, message = "Mobile no. should be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile no. should be at most 10 digits")
    private long mobileNo;

    @NotBlank(message = "password cannot be null")
    private String password;
}
