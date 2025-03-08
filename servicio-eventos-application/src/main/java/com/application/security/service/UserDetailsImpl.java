package com.application.security.service;

import com.infrastructure.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementación de la interfaz UserDetails de Spring Security.
 * Esta clase representa los detalles de un usuario, que se utilizan para la autenticación y autorización.
 *
 * @author Alberto Zapardiel Fernández
 */
public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor de la clase UserDetailsImpl.
     *
     * @param user el objeto User que contiene la información del usuario.
     */
    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * Devuelve las autoridades (roles) concedidas al usuario.
     *
     * @return una colección de objetos GrantedAuthority que representan los roles del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Devuelve la contraseña utilizada para autenticar al usuario.
     *
     * @return la contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el nombre de usuario utilizado para autenticar al usuario.
     *
     * @return el nombre de usuario.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indica si la cuenta del usuario no ha expirado.
     *
     * @return true si la cuenta no ha expirado, false si lo ha hecho.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario no está bloqueado.
     *
     * @return true si el usuario no está bloqueado, false si lo está.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario (contraseña) no han expirado.
     *
     * @return true si las credenciales no han expirado, false si lo han hecho.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado o deshabilitado.
     *
     * @return true si el usuario está habilitado, false si está deshabilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}