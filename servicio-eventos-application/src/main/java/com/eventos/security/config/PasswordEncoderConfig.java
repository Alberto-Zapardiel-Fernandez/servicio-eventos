package com.eventos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración para el codificador de contraseñas.
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Crea un codificador de contraseñas por defecto.
     *
     * @return El codificador de contraseñas creado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}