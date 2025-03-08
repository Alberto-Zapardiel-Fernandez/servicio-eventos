package com.application.controller;

import com.application.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Controlador para la autenticación de usuarios.
 * Este controlador gestiona el endpoint de inicio de sesión (/auth/login) y genera tokens JWT para los usuarios autenticados.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    /**
     * El AuthenticationManager es responsable de autenticar a los usuarios.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * El UserDetailsService es responsable de cargar los detalles de los usuarios.
     */
    private final UserDetailsService userDetailsService;

    /**
     * El JwtUtil es responsable de generar y validar los tokens JWT.
     */
    private final JwtUtil jwtUtil;

    /**
     * Crea un token de autenticación JWT para un usuario.
     *
     * @param credentials un mapa que contiene las credenciales del usuario (username y password).
     * @return un ResponseEntity que contiene el token JWT en caso de autenticación exitosa,
     * o un ResponseEntity con un mensaje de error en caso de autenticación fallida.
     */
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String, String> credentials) {
        try {
            // Autentica al usuario utilizando el AuthenticationManager.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"))
            );
            log.info("Autenticación exitosa");
        } catch (Exception e) {
            // Registra el error de autenticación y devuelve una respuesta de error.
            log.info("Autenticación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Credenciales incorrectas");
        }
        // Carga los detalles del usuario y genera un token JWT.
        final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.get("username"));
        final String jwt = jwtUtil.generateToken(userDetails);
        // Devuelve el token JWT en la respuesta.
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}