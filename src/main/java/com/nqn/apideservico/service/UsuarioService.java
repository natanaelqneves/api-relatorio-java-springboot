package com.nqn.apideservico.service;

import com.nqn.apideservico.model.Usuario;
import com.nqn.apideservico.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario buscarUsuarioPor(String nomeDeUsuario) {
        return repository.findByNomeDeUsuario(nomeDeUsuario);
    }
}
