package com.springboot.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRestController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage()
	{
		return "login-form";
	}
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
}
