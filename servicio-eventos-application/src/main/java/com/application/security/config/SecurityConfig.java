package com.application.security.config;

import com.application.security.jwt.JwtRequestFilter;
import com.application.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad para la aplicación.
 * Esta clase configura la seguridad web, incluyendo la autenticación y autorización.
 *
 * @author Alberto Zapardiel Fernández
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Configura la cadena de filtros de seguridad para las peticiones HTTP.
     *
     * @param http el objeto HttpSecurity que permite configurar la seguridad web.
     * @return un objeto SecurityFilterChain que define la cadena de filtros de seguridad.
     * @throws Exception si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita la protección CSRF (Cross-Site Request Forgery) ya que la autenticación basada en tokens es stateless.
                .csrf(AbstractHttpConfigurer::disable)
                // Configura la gestión de sesiones para que no se creen sesiones (STATELESS).
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura las reglas de autorización para las peticiones HTTP.
                .authorizeHttpRequests(auth -> auth
                        // Permite el acceso sin autenticación al endpoint "/auth/login".
                        .requestMatchers("/auth/login").permitAll()
                        // Requiere autenticación para cualquier otra petición.
                        .anyRequest().authenticated())
                // Agrega un filtro personalizado (JwtRequestFilter) antes del filtro UsernamePasswordAuthenticationFilter.
                .addFilterBefore(new JwtRequestFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configura el AuthenticationManager, que es responsable de la autenticación de usuarios.
     *
     * @param authenticationConfiguration la configuración de autenticación.
     * @return un objeto AuthenticationManager.
     * @throws Exception si ocurre un error al obtener el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura el PasswordEncoder, que se utiliza para codificar y verificar contraseñas.
     * En este caso, se utiliza BCryptPasswordEncoder, un algoritmo de hash seguro.
     *
     * @return un objeto PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
