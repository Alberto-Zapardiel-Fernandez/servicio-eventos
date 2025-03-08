package com.application.controller;

import com.application.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String, String> credentials) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Credenciales incorrectas");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.get("username"));
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}