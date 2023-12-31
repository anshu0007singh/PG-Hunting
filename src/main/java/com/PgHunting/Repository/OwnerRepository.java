package com.PgHunting.Repository;

import com.PgHunting.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByUsername(String username);
}
