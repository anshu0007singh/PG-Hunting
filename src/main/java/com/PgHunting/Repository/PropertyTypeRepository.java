package com.PgHunting.Repository;

import com.PgHunting.Entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType,Long> {

    PropertyType findByProperty(String property);
}
