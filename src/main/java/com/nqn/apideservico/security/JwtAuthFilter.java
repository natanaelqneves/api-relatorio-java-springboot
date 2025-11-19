package com.nqn.apideservico.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioDetailsService usuarioDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain

    ) throws ServletException, IOException {
        // 1. Extrai o token do cabeçalho "Authorization: Bearer <token>"
        // 2. Valida o token e extrai o username
        // 3. Se o token for válido, carrega o UserDetails e define a autenticação no SecurityContextHolder
    }
}
