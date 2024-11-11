package com.irojas.demojwt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.irojas.demojwt.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/auth/**").permitAll()              // Permitir los endpoints de autenticación
                .requestMatchers("/products/register").permitAll()     // Permitir los endpoint para registrar productos
                .requestMatchers("/products/listar").permitAll()       // Permitir los endpoint para listar productos
                .requestMatchers("/products/{id}").permitAll()      // Requiere autenticación para obtener producto por ID
                .requestMatchers("/products/{id}", "/products/{id}/*").permitAll() // Requiere autenticación para actualizar/eliminar producto por ID
                .requestMatchers("/categorias/listar").permitAll() 
                .anyRequest().authenticated()                          // Requiere autenticación para cualquier otra solicitud
            )
            .sessionManagement(sessionManager -> sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
