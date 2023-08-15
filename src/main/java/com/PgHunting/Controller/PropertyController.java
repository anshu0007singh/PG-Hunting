package com.PgHunting.Controller;

import com.PgHunting.Service.PropertyService;
import com.PgHunting.util.PropertyRequestDto;
import com.PgHunting.util.PropertyResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping("/register/{id}")
    public ResponseEntity<PropertyResponseDto> registerProperty(@PathVariable("id") long ownerId
            ,@Valid @RequestBody PropertyRequestDto propertyRequestDto){
        return new ResponseEntity<>(propertyService.registerProperty(ownerId,propertyRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getPropertyByPropertyId/{id}")
    public ResponseEntity<PropertyResponseDto> getPropertyByPropertyId(@PathVariable("id") long propertyId){
        return new ResponseEntity<>(propertyService.getPropertyByPropertyId(propertyId),HttpStatus.OK);
    }

    @PutMapping("updatePropertyByPropertyId/{id}")
    public ResponseEntity<PropertyResponseDto> updatePropertyByPropertyId(@PathVariable("id") long propertyId
            ,@Valid @RequestBody PropertyRequestDto propertyRequestDto){
        return new ResponseEntity<>(propertyService.updatePropertyByPropertyId(propertyId,propertyRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("deletePropertyByPropertyId/{id}")
    public ResponseEntity<String> deletePropertyByPropertyId(@PathVariable("id") long propertyId){
        return new ResponseEntity<>(propertyService.deletePropertyByPropertyId(propertyId),HttpStatus.OK);
    }
}
