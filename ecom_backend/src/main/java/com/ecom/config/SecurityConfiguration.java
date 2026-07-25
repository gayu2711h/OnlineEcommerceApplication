package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain customizeSecurityFilterChain(HttpSecurity http) throws Exception {
		
		 // Disable CSRF because we are building REST APIs
        http.csrf(csrf -> csrf.disable());

        // Do not create Http Session
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Enable HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Authorization Rules
        http.authorizeHttpRequests(request -> request

                // Public APIs
                .requestMatchers(
                        "/users/signup",
                        "/users/signin",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Anyone can view products and categories
                .requestMatchers(HttpMethod.GET,
                        "/products/**",
                        "/categories/**")
                .permitAll()

                // Only Admin can manage Categories
                .requestMatchers(
                        HttpMethod.POST,
                        "/categories")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/categories/**")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/categories/**")
                .hasRole("ADMIN")

                // Only Admin can manage Products
                .requestMatchers(
                        HttpMethod.POST,
                        "/products")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/products/**")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/products/**")
                .hasRole("ADMIN")

                // Customer Cart APIs
                .requestMatchers("/cart/**")
                .hasRole("CUSTOMER")

                // Customer Order APIs
                .requestMatchers("/orders/**")
                .hasRole("CUSTOMER")

                // Payment APIs
                .requestMatchers("/payments/**")
                .hasRole("CUSTOMER")

                // Remaining APIs require authentication
                .anyRequest()
                .permitAll());
                //.authenticated());

        return http.build();
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
