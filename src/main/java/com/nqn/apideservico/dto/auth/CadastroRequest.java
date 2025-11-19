package com.nqn.apideservico.dto.auth;

public record CadastroRequest(String email,
                              String matricula,
                              String nomeDeUsuario,
                              String senha) {
}
