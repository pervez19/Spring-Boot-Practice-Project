package com.springboot.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springboot.demo.entity.Employee;
import com.springboot.demo.service.EmployeeService;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private EmployeeService employeeService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");
		
			String username = authentication.getName();
			
			System.out.println("userName=" + username);
			
		Employee theUser = employeeService.findByUserName(username);
			
			// now place in the session
			HttpSession session = request.getSession();
			session.setAttribute("user", theUser);
			
			// forward to home page
			
			response.sendRedirect("/");

	}

}
