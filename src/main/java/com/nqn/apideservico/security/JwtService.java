package com.nqn.apideservico.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${application.security.jwt.chave-secreta}")
    private String chaveSecreta;

    // ... (Implementação dos métodos para extrair Username, gerar Token, validar Token)
}




