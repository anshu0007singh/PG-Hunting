package com.PgHunting.Service;

import com.PgHunting.util.RegisterDto;
import com.PgHunting.util.ResponseDto;

import java.util.List;

public interface OwnerService {

    // Register the owner details
    ResponseDto registerOwner(RegisterDto registerDto);

    //Get all Owners
    List<ResponseDto> getAllOwners();

    //Get Owner By Id
    ResponseDto getOwnerById(long ownerId);

    //update the owner details
    ResponseDto updateOwner(long ownerId, RegisterDto updatedRegisterDto);

    //delete the owner details
    void deleteById(long ownerId);
}
