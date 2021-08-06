package com.example.service;

import java.util.List;

import com.example.entities.Role;

public interface RoleService {
	List<Role> getAllRoles();

	Role createRole(Role role);

	Role updateRole(Long id, Role role);

	void deleteRole(Long roleId);

	Role findById(Long id);

}
