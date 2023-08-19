package com.PgHunting.Service.ServiceImpl;

import com.PgHunting.Exception.ResourceNotFoundException;
import com.PgHunting.Entity.Role;
import com.PgHunting.Repository.RoleRepository;
import com.PgHunting.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public String deleteRoleById(long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Property category with id :" + roleId + "is not found"));
        return "Role has been deleted Successfully";
    }

    @Override
    public Role updateRoleById(long roleId,Role role) {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(500,"Property category with id :" + roleId + "is not found"));
        existingRole.setRole(role.getRole());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
