package com.example.springboot;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	 @Override
	    public void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.csrf().disable().antMatcher("/**").authorizeRequests()
	                .antMatchers("/","/calender", "/login", "welcome").authenticated()
	                .antMatchers("/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .oauth2Login().permitAll()
	                .and()
	                .logout()
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutSuccessUrl("/");
	    }
}
