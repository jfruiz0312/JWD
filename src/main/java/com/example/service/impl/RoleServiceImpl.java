package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImpl  implements RoleService{
@Autowired
private RoleRepository roleRepository;
	@Override
	public List<Role> getAllRoles() {
		List<Role> roles= new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	@Override
	public Role createRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role updateRole(Long id, Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
