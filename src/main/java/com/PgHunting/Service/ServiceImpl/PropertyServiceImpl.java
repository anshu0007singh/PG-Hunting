package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Entity.Owner;
import com.PgHunting.Entity.Property;
import com.PgHunting.Entity.PropertyType;
import com.PgHunting.Repository.OwnerRepository;
import com.PgHunting.Repository.PropertyRepository;
import com.PgHunting.Repository.PropertyTypeRepository;
import com.PgHunting.Service.PropertyService;
import com.PgHunting.util.PropertyRequestDto;
import com.PgHunting.util.PropertyResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PropertyResponseDto registerProperty(long ownerId, PropertyRequestDto propertyRequestDto) {

        Property property = modelMapper.map(propertyRequestDto, Property.class);


        Owner owner = ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"owner with id: "+ownerId+" is not present"));
        property.setOwner(owner);

        Set<PropertyType> propertyTypes = propertyRequestDto.getType()
                .stream().map((type) -> propertyTypeRepository
                        .findByProperty(type)).collect(Collectors.toSet());

        property.setProperty_Type(propertyTypes);

        propertyRepository.save(property);
        return modelMapper.map(property,PropertyResponseDto.class);
    }

    @Override
    public PropertyResponseDto getPropertyByPropertyId(long propertyId) {
        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(500, "property with id: "+ propertyId +" does not exist"));
        return modelMapper.map(property,PropertyResponseDto.class);
    }

    @Override
    public PropertyResponseDto updatePropertyByPropertyId(long propertyId, PropertyRequestDto propertyRequestDto) {
        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(500, "property with id: "+ propertyId +" does not exist"));
        property.setCity(propertyRequestDto.getCity());
        property.setAddress(propertyRequestDto.getAddress());
        property.setPinCode(propertyRequestDto.getPinCode());
        property.setState(propertyRequestDto.getState());
        property.setDescription(propertyRequestDto.getDescription());
        property.setPrice(propertyRequestDto.getPrice());
        Set<PropertyType> propertyTypes = propertyRequestDto.getType().stream()
                .map((type) -> propertyTypeRepository.findByProperty(type)).collect(Collectors.toSet());
        property.setProperty_Type(propertyTypes);
        propertyRepository.save(property);
        return modelMapper.map(property, PropertyResponseDto.class);
    }

    @Override
    public String deletePropertyByPropertyId(long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(500, "property with id: "+ propertyId +" does not exist"));

        propertyRepository.delete(property);
        return "Property deleted Succesfully";
    }
}
