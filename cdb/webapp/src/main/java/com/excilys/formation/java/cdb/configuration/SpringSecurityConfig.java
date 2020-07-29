package com.excilys.formation.java.cdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Qualifier("customUserDetailsService")
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.GET,"/","/dashboard").hasAnyRole("user","ADMIN")
		.antMatchers(HttpMethod.POST,"/","/dashboard").hasRole("ADMIN")
		.antMatchers("/addComputer").hasRole("ADMIN")
		.antMatchers("/editComputer").hasRole("ADMIN")
		.and()
		.formLogin()
		.and()
		.logout();
		
//		http.authorizeRequests().antMatchers("/login", "/resources/**").permitAll()
//		.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/dashboard").permitAll()
//		.and().logout().logoutUrl("/logout").clearAuthentication(true)
//		.and().authorizeRequests().anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println(new BCryptPasswordEncoder().encode("password"));
		System.out.println(new BCryptPasswordEncoder().encode("azerty"));
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
