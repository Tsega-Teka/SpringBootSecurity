package com.SpringBootSecurity.securty;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//UserDetailsService 
	@Bean
	public UserDetailsService userDetailsService() {
		//for fake data
//		UserDetails admin = User.withUsername("tsega")
//				.password(encoder().encode("12345"))
//				.roles("ADMIN").build();
//		
//		UserDetails user = User.withUsername("Helen")
//				.password(encoder().encode("54321"))
//				.roles("USER").build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		
		// real db connection
		return new UserDetailsServiceImp();
		
	}
	
	
	//Authorization filter
	@Bean
	public SecurityFilterChain secrityfilltChain(HttpSecurity http) throws Exception {
		
		return
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests().requestMatchers("/api/v1/**").authenticated()
		.and()
		.authorizeHttpRequests().requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
		.and().httpBasic().and().build();
			
	}
	
	//paswordEncoder
	
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}
	
}
