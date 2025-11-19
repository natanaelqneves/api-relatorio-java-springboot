package com.nqn.apideservico.security;

import com.nqn.apideservico.exceptions.ResourceNotFoundException;
import com.nqn.apideservico.model.Usuario;
import com.nqn.apideservico.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeDeUsuario) throws UsernameNotFoundException {
        Usuario usuario =  usuarioRepository.findByNomeDeUsuario(nomeDeUsuario);

        if(usuario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado: " + nomeDeUsuario);
        }

        return usuario;
    }
}
