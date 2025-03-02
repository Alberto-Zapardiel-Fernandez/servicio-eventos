package com.eventos.rest;

import com.eventos.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controlador para la autenticación y generación de tokens JWT.
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param authenticationRequest El objeto con el nombre de usuario y la contraseña.
     * @return El token JWT generado.
     * @throws Exception Si ocurre un error durante la autenticación.
     */
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody Map<String, String> authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.get("username"), authenticationRequest.get("password"))
            );
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.get("username"));
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}