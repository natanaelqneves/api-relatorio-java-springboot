package com.nqn.apideservico.controller.auth;

import com.nqn.apideservico.dto.auth.AuthResponse;
import com.nqn.apideservico.dto.auth.CadastroRequest;
import com.nqn.apideservico.dto.auth.LoginRequest;
import com.nqn.apideservico.security.JwtService;
import com.nqn.apideservico.security.UsuarioDetailsService;
import com.nqn.apideservico.service.AuthUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthUsuarioService authUsuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AuthUsuarioService authUsuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authUsuarioService = authUsuarioService;
    }

    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return null;
    }

    public ResponseEntity<UsuarioDetailsService> register(@RequestBody CadastroRequest cadastroRequest){
        return null;
    }
}
