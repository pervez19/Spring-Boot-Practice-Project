package com.springboot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.RoleRepository;
import com.springboot.demo.entity.Role;

@Service
public class RoleServiceImp implements RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImp(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role findRoleByName(String name) {
		
		return roleRepository.findByName(name);
	}

}
