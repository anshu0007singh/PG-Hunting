package com.PgHunting.Service;

import com.PgHunting.util.RegisterDto;

import java.util.List;

public interface OwnerService {

    // Register the owner details
    RegisterDto registerOwner(RegisterDto registerDto);

    //Get all Owners
    List<RegisterDto> getAllOwners();

    //Get Owner By Id
    RegisterDto getOwnerById(long ownerId);

    //update the owner details
    RegisterDto updateOwner(long ownerId, RegisterDto updatedRegisterDto);

    //delete the owner details
    void deleteById(long ownerId);
}
