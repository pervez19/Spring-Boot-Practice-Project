package com.springboot.demo.service;

import com.springboot.demo.entity.Role;

public interface RoleService {

	public void save(Role role);
	public void deleteById(int id);
	public Role findRoleByName(String name);
}
