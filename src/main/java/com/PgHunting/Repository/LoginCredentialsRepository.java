package com.PgHunting.Repository;

import com.PgHunting.Model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials,Long> {
}
