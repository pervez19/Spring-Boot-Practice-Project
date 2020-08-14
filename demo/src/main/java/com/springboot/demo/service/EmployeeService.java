package com.springboot.demo.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.demo.entity.Employee;

public interface EmployeeService extends UserDetailsService {

	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee employee);
	public void deleteById(int id);
	public Employee findByUserName(String userName);
}
