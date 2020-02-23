package com.toys.shop.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.toys.shop.Entities.Account.AccountService;
import com.toys.shop.Jwt.JwtAuthenticationFilter;

@EnableWebSecurity
public class WebSecuriryConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AccountService accountService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().
		antMatchers("/admin/**").
		access("hasRole('Admin')");
		
		http.authorizeRequests()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403");
		
		http.authorizeRequests().
		antMatchers("/","/home","/api/**").permitAll().
		anyRequest().authenticated();
//		and().formLogin().
//		defaultSuccessUrl("/success").
//		permitAll().
//		and().
//		logout().
//		permitAll();
		
		http.addFilterBefore(jwtAuthenticationFilter(), 
				UsernamePasswordAuthenticationFilter.class);
	}
}
