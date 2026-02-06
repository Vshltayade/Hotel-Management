package com.vish.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vish.app.Services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// field injection
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomAuthenticationSuccessHander customAuthenticationSuccessHander;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


    // security filter chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/login", "/css/**", "/script/**", "/images/**", "/registration-process").permitAll()
					.requestMatchers("/", "/new-reservation", "/your-reservations").hasAnyRole("EMPLOYEE")
					.anyRequest().authenticated()
			)
			.formLogin(login -> login
					.loginPage("/login")
					.loginProcessingUrl("/login-process")
					.successHandler(customAuthenticationSuccessHander)
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/login")
					.permitAll()
			);
			
			return httpSecurity.build();
	}
    
    // dao auth bean
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userService);
    	authenticationProvider.setPasswordEncoder(passwordEncoder);
    	return authenticationProvider;
    }
}
