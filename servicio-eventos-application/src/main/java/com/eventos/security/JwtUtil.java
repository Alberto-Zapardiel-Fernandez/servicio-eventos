package com.eventos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Clase de utilidad para generar y validar tokens JWT.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Genera un token JWT para el nombre de usuario dado.
     *
     * @param username El nombre de usuario para el que se genera el token.
     * @return El token JWT generado.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Crea un token JWT con las reclamaciones y el sujeto dados.
     *
     * @param claims Las reclamaciones para el token.
     * @param subject El sujeto (nombre de usuario) para el token.
     * @return El token JWT creado.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario extraído.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     *
     * @param token El token JWT.
     * @return La fecha de expiración extraída.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae una reclamación específica del token JWT.
     *
     * @param token El token JWT.
     * @param claimsResolver El resolutor de reclamaciones.
     * @param <T> El tipo de la reclamación.
     * @return La reclamación extraída.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todas las reclamaciones del token JWT.
     *
     * @param token El token JWT.
     * @return Las reclamaciones extraídas.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Verifica si el token JWT ha expirado.
     *
     * @param token El token JWT.
     * @return `true` si el token ha expirado, `false` en caso contrario.
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida el token JWT para el nombre de usuario dado.
     *
     * @param token El token JWT.
     * @param username El nombre de usuario.
     * @return `true` si el token es válido, `false` en caso contrario.
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}