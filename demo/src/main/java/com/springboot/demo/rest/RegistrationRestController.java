package com.springboot.demo.rest;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.demo.crm.EmployeeRegDeto;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.service.EmployeeService;
import com.springboot.demo.service.RoleService;

@Controller
@RequestMapping("/register")
public class RegistrationRestController {


	private BCryptPasswordEncoder passwordEncoder;
	
	private RoleService roleService;
	private EmployeeService employeeService;

	@Autowired
	public RegistrationRestController(EmployeeService employeeService, RoleService roleService,BCryptPasswordEncoder passwordEncoder) {
		this.employeeService = employeeService;
		this.roleService=roleService;
		this.passwordEncoder=passwordEncoder;
	}
	
	@GetMapping("/registrationForm")
	public String registrationForm(Model model)
	{
		EmployeeRegDeto employeeRegDeto=new EmployeeRegDeto();
		model.addAttribute("employeeRegDeto",employeeRegDeto);
		return"registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("employeeRegDeto") EmployeeRegDeto employeeRegDeto,
			BindingResult theBindingResult, Model theModel){
		
		//form validation
		if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }
		String userName=employeeRegDeto.getUsername();
		Employee existing = employeeService.findByUserName(userName);
	        if (existing != null){
	        	theModel.addAttribute("employeeRegDeto", new EmployeeRegDeto());
				
	        	theModel.addAttribute("registrationError", "User name already exists.");
	        	return "registration-form";
	        }

	        Employee employee=new Employee();
	        employee.setUserName(employeeRegDeto.getUsername());
	        employee.setEmail(employeeRegDeto.getEmail());
	        employee.setFirstName(employeeRegDeto.getFirstName());
	        employee.setLastName(employeeRegDeto.getLastName());
	       employee.setPassword(passwordEncoder.encode(employeeRegDeto.getPassword()));	   
	        employee.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_EMPLOYEE")));
	        
	        employeeService.save(employee);  
	        
		return "redirect:/register/registrationForm?success";
		
	}
	
	
}
