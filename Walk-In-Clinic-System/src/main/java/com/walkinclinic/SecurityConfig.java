package com.walkinclinic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	// 🔐 Admin-side: Spring Session + form login
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilter(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout=true")
            )
            .sessionManagement(session -> session
                .maximumSessions(1)  // Optional: Limit to one session per admin
            );

        return http.build();
    }

    // 🔐 Patient/API side: Stateless JWT security
    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurity(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            .securityMatcher("/api/**")
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/api/evaluate-login").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
	    return new JwtAuthFilter(jwtService, userDetailsService);
	}
	
	  
}
