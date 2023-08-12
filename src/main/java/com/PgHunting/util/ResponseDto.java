package com.PgHunting.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private long mobileNo;
}
