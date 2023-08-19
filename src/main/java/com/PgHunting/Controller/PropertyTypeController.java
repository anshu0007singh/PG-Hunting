package com.PgHunting.Controller;

import com.PgHunting.Entity.PropertyType;
import com.PgHunting.Service.PropertyTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("PropertyCategories")
public class PropertyTypeController {

    @Autowired
    PropertyTypeService propertyTypeService;

    @PostMapping("/createNewCategory")
    public ResponseEntity<PropertyType> createNewCategory(@Valid @RequestBody PropertyType propertyType){
        return new ResponseEntity<>(propertyTypeService.createNewPropertyCategory(propertyType),HttpStatus.CREATED);
    }

    @PutMapping("/updateCategoryById/{id}")
    public ResponseEntity<PropertyType> updateCategoryById(@PathVariable("id") long propertyId,@Valid @RequestBody PropertyType propertyType){
        return new ResponseEntity<>(propertyTypeService.updatePropertyTypeById(propertyId,propertyType),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategoryById/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") long propertyId){
        return new ResponseEntity<>(propertyTypeService.deletePropertyTypeById(propertyId),HttpStatus.OK);
    }

    @GetMapping("/getAllPropertyType")
    public ResponseEntity<List<PropertyType>> getAllPropertyType(){
        return new ResponseEntity(propertyTypeService.getAllPropertyType(), HttpStatus.OK);
    }

}
