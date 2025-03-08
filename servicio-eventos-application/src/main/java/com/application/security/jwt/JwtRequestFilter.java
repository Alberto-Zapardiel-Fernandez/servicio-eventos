package com.application.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de solicitud JWT.
 * Este filtro intercepta las peticiones HTTP para validar el token JWT en el encabezado "Authorization".
 * Si el token es válido, autentica al usuario y lo establece en el contexto de seguridad.
 *
 * @author Alberto Zapardiel Fernández
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Realiza el filtrado de la solicitud para validar el token JWT.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @param chain    la cadena de filtros.
     * @throws ServletException si ocurre un error en el servlet.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        // Permite el acceso sin validación de token al endpoint "/auth/login".
        if (request.getServletPath().equals("/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        // Obtiene el encabezado "Authorization" de la solicitud.
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Extrae el token JWT del encabezado.
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                // Extrae el nombre de usuario del token JWT.
                username = jwtUtil.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                log.info("No se puede obtener el token JWT");
            } catch (ExpiredJwtException e) {
                log.info("El token JWT ha expirado");
            }
        } else {
            logger.warn("El token JWT no comienza con Bearer String");
        }

        // Valida el token y autentica al usuario si es válido.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(jwtUtil.validateToken(jwtToken, userDetails))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // Continúa con la cadena de filtros.
        chain.doFilter(request, response);
    }
}