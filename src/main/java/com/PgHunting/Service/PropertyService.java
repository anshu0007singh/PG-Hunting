package com.PgHunting.Service;

import com.PgHunting.Model.PropertyType;
import com.PgHunting.util.PropertyRequestDto;
import com.PgHunting.util.PropertyResponseDto;

import java.util.List;
import java.util.Properties;

public interface PropertyService {

    PropertyResponseDto registerProperty(long ownerId, PropertyRequestDto propertyRequestDto);

    PropertyResponseDto getPropertyByPropertyId(long propertyId);

    PropertyResponseDto updatePropertyByPropertyId(long propertyId, PropertyRequestDto propertyRequestDto);

    String deletePropertyByPropertyId(long propertyId);


}
