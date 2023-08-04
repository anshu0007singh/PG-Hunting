package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Model.LoginCredentials;
import com.PgHunting.Model.Owner;
import com.PgHunting.Model.Role;
import com.PgHunting.Repository.LoginCredentialsRepository;
import com.PgHunting.Repository.OwnerRepository;
import com.PgHunting.Repository.RoleRepository;
import com.PgHunting.Service.OwnerService;
import com.PgHunting.util.RegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RegisterDto registerOwner(RegisterDto registerDto) {

        //Modelmapper conversion dto to entity
        Owner owner = modelMapper.map(registerDto, Owner.class);
        ownerRepository.save(owner);

        //setting up the login credentials
        LoginCredentials newLoginCredentials = new LoginCredentials();
        newLoginCredentials.setUsername(registerDto.getUserName());
        newLoginCredentials.setPassword(registerDto.getPassword());
        newLoginCredentials.setEmail(registerDto.getEmail());
        List<Role> role = new ArrayList<>();
        role.add(roleRepository.findByRole("ROLE_OWNER"));
        Set<Role> roles = role.stream().collect(Collectors.toSet());
        newLoginCredentials.setRoles(roles);
        loginCredentialsRepository.save(newLoginCredentials);

        //Modelmapper conversion entity to dto
        RegisterDto registerDto1 = modelMapper.map(owner,RegisterDto.class);
        return registerDto1;
    }

    @Override
    public List<RegisterDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map((owner) -> modelMapper.map(owner, RegisterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegisterDto getOwnerById(long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Null Pointer Exception"));
        return modelMapper.map(owner, RegisterDto.class);
    }

    @Override
    public RegisterDto updateOwner(long ownerId, RegisterDto updatedRegisterDto) {
        Owner newOwner = modelMapper.map(updatedRegisterDto, Owner.class);
        Owner existingOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Null Pointer Exception"));
        existingOwner.setEmail(newOwner.getEmail());
        existingOwner.setName(newOwner.getName());
        existingOwner.setMobileNo(newOwner.getMobileNo());
        existingOwner.setUsername(newOwner.getUsername());
        ownerRepository.save(existingOwner);
        return modelMapper.map(newOwner,RegisterDto.class);
    }

    @Override
    public void deleteById(long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Null Pointer Exception"));
        ownerRepository.deleteById(owner.getId());
    }
}
