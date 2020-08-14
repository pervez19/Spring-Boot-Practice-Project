package com.springboot.demo.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.demo.entity.Employee;
import com.springboot.demo.service.EmployeeService;

@Controller
public class EmployeeRestController {
  
	private EmployeeService employeeService;

	@Autowired
	private Model themodel;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public String showHomePage(Model model,Principal principal)
	{
		String username=principal.getName();
		Employee employee=employeeService.findByUserName(username);
		model.addAttribute("employee", employee);
		return "home";
	}
	
	
	@GetMapping("/list")
	public String listOfEmployees(Model model)
	{
		List<Employee> employees=employeeService.findAll();
		model.addAttribute("employees", employees);
		
		return "Employee-list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id,Model model)
	{
		
		employeeService.deleteById(id);
		return "redirect:/list";
	}
	
	@GetMapping("/showFromForUpdate")
	public String showFromForUpdate(@RequestParam("employeeId") int id,Model model)
	{
		
		Employee employee=employeeService.findById(id);
		
		model.addAttribute("employee",employee);
		return "Employee-Form";
	}
	@PostMapping("/save")
	public String addEmployee(@ModelAttribute("employee") Employee employee)
	{
		employeeService.save(employee);
		return "redirect:/";
		
	}
}
