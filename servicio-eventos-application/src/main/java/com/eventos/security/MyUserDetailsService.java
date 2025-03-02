package com.eventos.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio para cargar los detalles del usuario desde la base de datos.
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    /**
     * Carga los detalles del usuario por nombre de usuario.
     *
     * @param username El nombre de usuario.
     * @return Los detalles del usuario.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí debes cargar el usuario desde tu base de datos o cualquier otra fuente de datos
        // Por ahora, vamos a simular un usuario en memoria
        String encodedPassword = passwordEncoder.encode("password"); // Aunque no se codifica, es importante usar el passwordEncoder
        return new User("admin", encodedPassword, new ArrayList<>());
    }
}