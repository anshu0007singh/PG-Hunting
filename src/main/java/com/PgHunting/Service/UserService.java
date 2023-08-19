package com.PgHunting.Service;

import com.PgHunting.util.RegisterDto;
import com.PgHunting.util.ResponseDto;

import java.util.List;

public interface UserService {

    ResponseDto register(RegisterDto registerDto);

    ResponseDto getUserById(long userId);

    ResponseDto getUserByUsername(String username);

    List<ResponseDto> getAllUsers();

    ResponseDto updateUserById(long userId, RegisterDto registerDto);

    String deleteUserById(long userId);
}
