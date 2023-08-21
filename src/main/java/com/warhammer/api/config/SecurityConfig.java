package com.warhammer.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	// We're loading the JWT filter and the authentication provider to allow identification.
    private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	// The starts of our filter chains that will determine the routes and who can access to it.
	@Bean
	SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
        // Allowing all cors for the moments for easier development.
        .cors((cors) -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOrigin("*"); // Allow all origins. You can restrict it to specific origins if needed.
                config.addAllowedMethod("*"); // Allow all HTTP methods.
                config.addAllowedHeader("*"); // Allow all headers.
                config.setAllowCredentials(false); // Allow credentials like cookies, authentication headers, etc.
                return config;
            }))
        // Disabling csrf blocking everybody from posting in the DB.
        .csrf((csrf) -> csrf.disable())
        // Defining the routes and who can access to it.
        .authorizeHttpRequests((auth) -> auth
        				.requestMatchers("/login").permitAll()
        				.requestMatchers("/register").permitAll()
        				.requestMatchers("/admin").hasRole("ADMIN")
        				.requestMatchers("/public/*").permitAll()
                        .anyRequest()
                        .authenticated()
        )
        // Defining a system of session that isn't stoked on the server.
        .sessionManagement((auth) -> auth
        			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		)
        // using authenticationProvider to allow access to authorized user.
        .authenticationProvider(authenticationProvider)
        // Verifying the token sent by the user with his request.
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
				        .cors((cors) -> cors.configurationSource(request -> {
				            CorsConfiguration config = new CorsConfiguration();
				            config.addAllowedOrigin("*"); // Allow all origins. You can restrict it to specific origins if needed.
				            config.addAllowedMethod("*"); // Allow all HTTP methods.
				            config.addAllowedHeader("*"); // Allow all headers.
				            config.setAllowCredentials(false); // Allow credentials like cookies, authentication headers, etc.
				            return config;
				        }))
                        .csrf((csrf) -> csrf.disable())
                        .authorizeHttpRequests((auth) -> auth
                                        .requestMatchers("/login").permitAll()
                                        .requestMatchers("/register").permitAll()
                                        .requestMatchers("/admin/*").hasRole("ADMIN")
                                        .requestMatchers("/user/*").hasRole("USER")
                                        .requestMatchers("/public/*").permitAll()
                                        .anyRequest()
                                        .authenticated()
                        )
                        .sessionManagement((auth) -> auth
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
		        .authenticationProvider(authenticationProvider)
		        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
*/
