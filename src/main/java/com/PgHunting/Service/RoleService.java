package com.PgHunting.Service;

import com.PgHunting.Model.PropertyType;
import com.PgHunting.Model.Role;

import java.util.List;

public interface RoleService {

    Role createNewRole(Role role);

    String deleteRoleById(long id);

    Role updateRoleById(long roleId, Role role);

    List<Role> getAllRole();
}
