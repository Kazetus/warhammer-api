package com.warhammer.api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.warhammer.api.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// Filter for JWT connection.
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService = new JwtService();
	@Autowired
	private UserDetailsService userDetailsService;
	// filtering the user if token is valid
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse reponse,
			@NonNull FilterChain filterChain
			) throws ServletException, IOException {
		// Getting the token in the Header of the request
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String username;
		// Verifying the validity of the token.
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, reponse);
			return;
		}
		// Extracting the username from the token
		jwt = authHeader.substring(7);
		username = jwtService.extractUsername(jwt);
		// Checking if the context already have an authentication
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			// Get the user from the DB
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			// Check the validity of the token comparing it's information and the DB
			if(jwtService.isTokenValid(jwt, userDetails)) {
				//Creating the token
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, reponse);
	}
}