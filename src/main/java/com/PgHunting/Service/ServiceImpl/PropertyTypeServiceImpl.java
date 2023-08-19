package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Entity.PropertyType;
import com.PgHunting.Repository.PropertyTypeRepository;
import com.PgHunting.Service.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {

    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @Override
    public PropertyType createNewPropertyCategory(PropertyType propertyType) {
        return propertyTypeRepository.save(propertyType);
    }

    @Override
    public String deletePropertyTypeById(long propertyId) {
        PropertyType propertyType = propertyTypeRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(500, "Property category with id :"+ propertyId + "is not found"));
        propertyTypeRepository.delete(propertyType);
        return "Property type deleted sucessfully";

    }

    @Override
    public PropertyType updatePropertyTypeById(long propertyId, PropertyType propertyType) {
        PropertyType existingPropertyType = propertyTypeRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Property category with id :" + propertyId + "is not found"));
        existingPropertyType.setProperty(propertyType.getProperty());
        return propertyTypeRepository.save(existingPropertyType);
    }

    @Override
    public List<PropertyType> getAllPropertyType() {
        return propertyTypeRepository.findAll();
    }
}
