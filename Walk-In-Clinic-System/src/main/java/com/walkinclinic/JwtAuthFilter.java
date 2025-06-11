package com.walkinclinic;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;
	
	 public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
	        this.jwtService = jwtService;
	        this.userDetailsService = userDetailsService;
	    }

	    @Override
	    protected void doFilterInternal(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            FilterChain filterChain)
	    
	            throws ServletException, IOException {

	        final String authHeader = request.getHeader("Authorization");
	        final String jwt;
	        final String username;

	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        
	        jwt = authHeader.substring(7);
	        username = jwtService.extractUsername(jwt);
	        
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        	
	        	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        	
	        	if (jwtService.isTokenValid(jwt, userDetails)) {
	        		
	        		var authToken = jwtService.getAuthenticationToken(jwt, userDetails, request);
	        		SecurityContextHolder.getContext().setAuthentication(authToken);
	        	}
	        	
	        }
	        
	        filterChain.doFilter(request, response);
	

    	}
}
