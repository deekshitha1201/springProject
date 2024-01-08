package com.example.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Filter.JwtAuthFilter;
import com.example.Service.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurity {
	 @Autowired
	    JwtAuthFilter jwtAuthFilter;
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailServiceImpl();
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf().disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/userregistration","/user/login","/user/sendotp","/user/verify-otp").permitAll()
	                .and()
	                .authorizeHttpRequests().requestMatchers("/user/**")
	                .authenticated()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .authenticationProvider(authenticationProvider())
	                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

	    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	

}
