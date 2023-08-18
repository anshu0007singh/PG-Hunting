package com.PgHunting.Service;

import com.PgHunting.Model.PropertyType;

import java.util.List;

public interface PropertyTypeService {

    PropertyType createNewPropertyCategory(PropertyType propertyType);

    String deletePropertyTypeById(long id);

    PropertyType updatePropertyTypeById(long PropertyId, PropertyType propertyType);

    List<PropertyType> getAllPropertyType();


}
