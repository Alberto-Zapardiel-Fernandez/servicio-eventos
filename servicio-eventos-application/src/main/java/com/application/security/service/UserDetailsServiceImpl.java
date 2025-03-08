package com.application.security.service;

import com.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa la interfaz UserDetailsService de Spring Security.
 * Este servicio se encarga de cargar los detalles de un usuario a partir de su nombre de usuario.
 *
 * @author Alberto Zapardiel Fernández
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Carga los detalles de un usuario a partir de su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario cuyos detalles se van a cargar.
     * @return un objeto UserDetails que contiene los detalles del usuario.
     * @throws UsernameNotFoundException si no se encuentra ningún usuario con el nombre de usuario dado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Buscando usuario: {}", username);
        com.infrastructure.entities.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new UserDetailsImpl(user);
    }
}