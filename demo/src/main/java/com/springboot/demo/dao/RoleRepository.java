package com.springboot.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String Name);

}
