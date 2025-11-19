package com.nqn.apideservico.dto.auth;

public class AuthResponse {

    private String token;
    private String nomeDeUsuario;

    public AuthResponse() {
    }

    public AuthResponse(String token, String nomeDeUsuario) {
        this.token = token;
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }
}
