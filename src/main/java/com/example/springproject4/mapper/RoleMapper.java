package com.example.springproject4.mapper;

import com.example.springproject4.dto.request.user.RoleRequest;
import com.example.springproject4.dto.response.user.RoleResponse;
import com.example.springproject4.entity.Role;

import java.util.List;

public class RoleMapper {
    public static Role fromRoleRequest(RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getRoleName());
        role.setUsers(List.of());

        return role;
    }

    public static RoleResponse fromRole(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoleName(role.getName());
        roleResponse.setUsers(role.getUsers().stream().map(u->u.getName()).toList());
        return roleResponse;
    }
}
