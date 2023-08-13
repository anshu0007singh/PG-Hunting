package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Model.LoginCredentials;
import com.PgHunting.Model.Role;
import com.PgHunting.Model.User;
import com.PgHunting.Repository.LoginCredentialsRepository;
import com.PgHunting.Repository.RoleRepository;
import com.PgHunting.Repository.UserRepository;
import com.PgHunting.Service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseDto register(RegisterDto registerDto) {

        // save the login credentials
        LoginCredentials loginCredentials = modelMapper.map(registerDto, LoginCredentials.class);
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepository.findByRole("ROLE_USER"));
        Set<Role> role = roles.stream().collect(Collectors.toSet());
        loginCredentials.setRoles(role);
        loginCredentialsRepository.save(loginCredentials);

        //save the User
        User user = modelMapper.map(registerDto,User.class);
        userRepository.save(user);

        return modelMapper.map(user, ResponseDto.class);
    }

    @Override
    public ResponseDto getUserById(long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(500," User with Id: "+ userId +"doesnot exist"));
        return modelMapper.map(user,ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map((user) -> modelMapper.map(user, ResponseDto.class) ).collect(Collectors.toList());
    }

    @Override
    public ResponseDto updateUserById(long userId, RegisterDto registerDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(500, "User with Id: "+ userId+ "doesnot exist"));

        User newUser = modelMapper.map(registerDto, User.class);
        existingUser.setUsername(newUser.getUsername());
        existingUser.setName(newUser.getName());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setMobileNo(newUser.getMobileNo());

        LoginCredentials existingloginCredentials = loginCredentialsRepository.findByUsername(existingUser.getUsername());
        if(existingloginCredentials == null){
            throw new ResourceNotFoundException(500, "User with Id: "+ userId+ "doesnot exist");
        }

        LoginCredentials newloginCredentials = modelMapper.map(registerDto, LoginCredentials.class);
        existingloginCredentials.setPassword(newloginCredentials.getPassword());
        existingloginCredentials.setEmail(newloginCredentials.getEmail());
        existingloginCredentials.setUsername(newloginCredentials.getUsername());

        return modelMapper.map(registerDto, ResponseDto.class);
    }

    @Override
    public String deleteUserById(long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"User with Id: "+ userId+ "doesnot exist"));

        LoginCredentials loginCredentials = loginCredentialsRepository.findByUsername(user.getUsername());
        if(loginCredentials == null){
            throw new ResourceNotFoundException(500,"User with Id: "+ userId+ "doesnot exist");
        }

        userRepository.deleteById(userId);
        loginCredentialsRepository.deleteById(loginCredentials.getId());

        return "Deleted Succesfully";
    }
}
