package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//add out users for in memory authentication
		//DEPRECATED METHOD BUT DOESNT MATHER
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("test123").roles("MANAGER"))
			.withUser(users.username("susan").password("test123").roles("ADMIN")
					);	
		
	}

	
	//configure the security web path, aplication, login, logout, etc.	
	//use of the tool -> source - overrride/implement mthods 	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //http secuirty, we use for spring secutiry config
		
		http.authorizeRequests()
		.anyRequest().authenticated()  //must be authenticahted..log in
		.and()  //and condition
		.formLogin() //customization of form login
			.loginPage("/showMyLoginPage")  // new link for the login page
			.loginProcessingUrl("/authenticateTheUser") // spring security for check user, this is automagic for spring
			.permitAll();								// as long we give the data or fields -> user pass 
			// permitAll() -> allow everyone to see the login page
		
	}

	
	
	
}
