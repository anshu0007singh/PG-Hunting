package com.PgHunting.Controller;

import com.PgHunting.Service.OwnerService;
import com.PgHunting.util.PropertyResponseDto;
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
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    //Register the owner inside the database
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerOwner(@Valid @RequestBody RegisterDto registerDto){
        ResponseDto response = ownerService.registerOwner(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get info of all the owners from database
    @GetMapping("/getAllOwners")
    public ResponseEntity<List<ResponseDto>> getAllOwners(){
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    @GetMapping("/getAllPropertiesByOwnerId/{id}")
    public ResponseEntity<List<PropertyResponseDto>> getAllPropertiesByOwnerId(@PathVariable("id") long ownerId){
        return new ResponseEntity<>(ownerService.getAllPropertiesByOwnerId(ownerId),HttpStatus.OK);
    }

    @GetMapping("/getOwnerByUsername/{username}")
    public ResponseEntity<ResponseDto> getOwnerByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(ownerService.getOwnerByUsername(username),HttpStatus.OK);
    }

    //Get info of the owner by Id
    @GetMapping("/getOwnerById/{id}")
    public ResponseEntity<ResponseDto> getOwnerById(@PathVariable("id") long ownerId){
        return new ResponseEntity<>(ownerService.getOwnerById(ownerId), HttpStatus.OK);
    }

    //Update the data of owner inside the database
    @PutMapping("/updateOwner/{id}")
    public ResponseEntity<ResponseDto> updateOwner(@PathVariable("id") long ownerId, @RequestBody RegisterDto updatedRegisterDto){
        return new ResponseEntity<>(ownerService.updateOwner(ownerId,updatedRegisterDto),HttpStatus.OK);
    }

    //Delete owner from database by Id
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long ownerId){
        ownerService.deleteById(ownerId);
        return new ResponseEntity<>("Data deleted Successfully",HttpStatus.OK);
    }

}
