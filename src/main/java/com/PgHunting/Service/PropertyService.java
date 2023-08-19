package com.PgHunting.Service;

import com.PgHunting.util.PropertyRequestDto;
import com.PgHunting.util.PropertyResponseDto;

public interface PropertyService {

    PropertyResponseDto registerProperty(long ownerId, PropertyRequestDto propertyRequestDto);

    PropertyResponseDto getPropertyByPropertyId(long propertyId);

    PropertyResponseDto updatePropertyByPropertyId(long propertyId, PropertyRequestDto propertyRequestDto);

    String deletePropertyByPropertyId(long propertyId);


}
