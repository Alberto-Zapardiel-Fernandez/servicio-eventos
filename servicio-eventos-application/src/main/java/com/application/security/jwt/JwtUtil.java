package com.application.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilidad para la gestión de tokens JWT (JSON Web Tokens).
 * Esta clase proporciona métodos para generar, extraer información y validar tokens JWT.
 *
 * @author Alberto Zapardiel Fernández
 */
@Component
public class JwtUtil {

    private final String secretKey;

    /**
     * Constructor de la clase JwtUtil.
     *
     * @param secretKey la clave secreta utilizada para firmar los tokens JWT. Se inyecta desde la configuración de la aplicación.
     */
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Genera un token JWT para un usuario.
     *
     * @param userDetails los detalles del usuario para el cual se va a generar el token.
     * @return el token JWT generado.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crea un token JWT con los claims y el sujeto especificados.
     *
     * @param claims  los claims (afirmaciones) que se van a incluir en el token.
     * @param subject el sujeto del token (normalmente el nombre de usuario).
     * @return el token JWT creado.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario de un token JWT.
     *
     * @param token el token JWT del cual se va a extraer el nombre de usuario.
     * @return el nombre de usuario extraído del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae un claim específico de un token JWT.
     *
     * @param token          el token JWT del cual se va a extraer el claim.
     * @param claimsResolver una función que define cómo extraer el claim de los Claims.
     * @param <T>            el tipo del claim a extraer.
     * @return el claim extraído del token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims de un token JWT.
     *
     * @param token el token JWT del cual se van a extraer los claims.
     * @return un objeto Claims que contiene todos los claims del token.
     */
    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Valida un token JWT.
     *
     * @param token       el token JWT a validar.
     * @param userDetails los detalles del usuario contra los cuales se va a validar el token.
     * @return true si el token es válido, false si no lo es.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extrae la fecha de expiración de un token JWT.
     *
     * @param token el token JWT del cual se va a extraer la fecha de expiración.
     * @return la fecha de expiración extraída del token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token el token JWT a verificar.
     * @return true si el token ha expirado, false si no lo ha hecho.
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}