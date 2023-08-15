package com.PgHunting.Controller;

import com.PgHunting.Service.ServiceImpl.UserServiceImpl;
import com.PgHunting.util.RegisterDto;
import com.PgHunting.util.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable("id") long userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<ResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<ResponseDto> updateUserById(@PathVariable("id") long userId, @RequestBody RegisterDto updatedregisterDto){
        return new ResponseEntity<>(userService.updateUserById(userId,updatedregisterDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long userId){
        return  new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
    }
}
