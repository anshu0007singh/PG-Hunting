package com.PgHunting.Controller;

import com.PgHunting.Service.OwnerService;
import com.PgHunting.util.RegisterDto;
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
    public ResponseEntity<RegisterDto> registerOwner(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(ownerService.registerOwner(registerDto), HttpStatus.CREATED);
    }

    //Get info of all the owners from database
    @GetMapping("/getAllOwners")
    public ResponseEntity<List<RegisterDto>> getAllOwners(){
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    //Get info of the owner by Id
    @GetMapping("/getOwnerById/{id}")
    public ResponseEntity<RegisterDto> getOwnerById(@PathVariable("id") long ownerId){
        return new ResponseEntity<>(ownerService.getOwnerById(ownerId), HttpStatus.OK);
    }

    //Update the data of owner inside the database
    @PutMapping("/updateOwner/{id}")
    public ResponseEntity<RegisterDto> updateOwner(@PathVariable("id") long ownerId, @RequestBody RegisterDto updatedRegisterDto){
        return new ResponseEntity<>(ownerService.updateOwner(ownerId,updatedRegisterDto),HttpStatus.OK);
    }

    //Delete owner from database by Id
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long ownerId){
        ownerService.deleteById(ownerId);
        return new ResponseEntity<>("Data deleted Successfully",HttpStatus.OK);
    }

}
