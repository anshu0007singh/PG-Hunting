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
import com.PgHunting.util.ResponseDto;
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
    public ResponseDto registerOwner(RegisterDto registerDto) {

        //Modelmapper conversion dto to entity
        Owner owner = modelMapper.map(registerDto, Owner.class);
        ownerRepository.save(owner);

        //setting up the login credentials
        LoginCredentials newLoginCredentials = modelMapper.map(registerDto, LoginCredentials.class);
        List<Role> role = new ArrayList<>();
        role.add(roleRepository.findByRole("ROLE_OWNER"));
        Set<Role> roles = role.stream().collect(Collectors.toSet());
        newLoginCredentials.setRoles(roles);
        loginCredentialsRepository.save(newLoginCredentials);

        return modelMapper.map(owner, ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map((owner) -> modelMapper.map(owner, ResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto getOwnerById(long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500," user with id: "+ ownerId +" does not exist"));
        return modelMapper.map(owner, ResponseDto.class);
    }

    @Override
    public ResponseDto updateOwner(long ownerId, RegisterDto updatedRegisterDto) {

        //changes in owner
        Owner newOwner = modelMapper.map(updatedRegisterDto, Owner.class);
        Owner existingOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500," user with id: "+ ownerId +" does not exist"));

        LoginCredentials existingdetails = loginCredentialsRepository.findByUsername(existingOwner.getUsername());
        if(existingdetails==null){
            throw new ResourceNotFoundException(500," user with id: "+ ownerId +" does not exist");
        }
        existingOwner.setEmail(newOwner.getEmail());
        existingOwner.setName(newOwner.getName());
        existingOwner.setMobileNo(newOwner.getMobileNo());
        existingOwner.setUsername(newOwner.getUsername());
        ownerRepository.save(existingOwner);

        //changes in login credentials
        LoginCredentials newloginCredentials = modelMapper.map(updatedRegisterDto,LoginCredentials.class);
        existingdetails.setPassword(newloginCredentials.getPassword());
        existingdetails.setUsername(newloginCredentials.getUsername());
        existingdetails.setEmail(newloginCredentials.getEmail());
        loginCredentialsRepository.save(existingdetails);


        return modelMapper.map(existingOwner,ResponseDto.class);
    }

    @Override
    public void deleteById(long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500," user with id: "+ ownerId +" does not exist"));
        ownerRepository.deleteById(owner.getId());

        LoginCredentials logincredentials = loginCredentialsRepository.findByUsername(owner.getUsername());
        if(logincredentials==null){
            new ResourceNotFoundException(500, " user with id: "+ ownerId +" does not exist");
        }
        loginCredentialsRepository.deleteById(logincredentials.getId());
    }
}
