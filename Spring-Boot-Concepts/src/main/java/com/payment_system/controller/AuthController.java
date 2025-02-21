package com.payment_system.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment_system.model.RefreshToken;
import com.payment_system.repository.RefreshTokenRepository;
import com.payment_system.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthController(JwtUtil jwtUtil, RefreshTokenRepository refreshTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }

        String token = refreshToken.substring(7);
        Optional<RefreshToken> storedToken = refreshTokenRepository.findByToken(token);

        if (storedToken.isPresent() && jwtUtil.validateToken(token, true)) {
            String newAccessToken = jwtUtil.generateAccessToken(storedToken.get().getUsername());
            return ResponseEntity.ok().body("New Access Token: " + newAccessToken);
        }

        return ResponseEntity.badRequest().body("Invalid or expired refresh token");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String refreshToken) {
        String token = refreshToken.substring(7);
        refreshTokenRepository.deleteByUsername(jwtUtil.extractUsername(token, true));
        return ResponseEntity.ok("Logged out successfully");
    }

    
}
