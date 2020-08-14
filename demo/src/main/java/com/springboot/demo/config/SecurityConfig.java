package com.springboot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.demo.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {


    //beans
	//bcrypt bean definition
		@Autowired
		private EmployeeService employeeService;
		
		@Autowired
	    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		//authenticationProvider bean definition
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(employeeService); //set the custom user details service
			auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
			return auth;
		}
		
		  @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		        auth.authenticationProvider(authenticationProvider());
		    }
		  
		  @Override
			protected void configure(HttpSecurity http) throws Exception {

				http.authorizeRequests()
					.antMatchers("/").hasRole("EMPLOYEE")
					.antMatchers("employees/leaders/**").hasRole("MANAGER")
					.antMatchers("/list/**").hasRole("ADMIN")
					.antMatchers("/resources/**").permitAll()
					.and()
					.formLogin()
						.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.successHandler(customAuthenticationSuccessHandler)
						.permitAll()
					.and()
					.logout().permitAll()
					.and()
					.exceptionHandling().accessDeniedPage("/access-denied");
				
			}
		  
		  
}
