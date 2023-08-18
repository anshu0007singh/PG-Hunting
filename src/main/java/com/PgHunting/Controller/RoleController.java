package com.PgHunting.Controller;

import com.PgHunting.Model.PropertyType;
import com.PgHunting.Model.Role;
import com.PgHunting.Service.PropertyTypeService;
import com.PgHunting.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/createNewCRole")
    public ResponseEntity<Role> createNewRole(@Valid @RequestBody Role role){
        return new ResponseEntity<>(roleService.createNewRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/updateRoleById/{id}")
    public ResponseEntity<Role> updateRoleById(@PathVariable("id") long roleId,@Valid @RequestBody Role role){
        return new ResponseEntity<>(roleService.updateRoleById(roleId, role),HttpStatus.OK);
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable("id") long roleId){
        return new ResponseEntity<>(roleService.deleteRoleById(roleId),HttpStatus.OK);
    }

    @GetMapping("/getAllRole")
    public ResponseEntity<List<PropertyType>> getAllPropertyType(){
        return new ResponseEntity(roleService.getAllRole(), HttpStatus.OK);
    }
}
