package com.nqn.apideservico.repository;

import com.nqn.apideservico.model.Relatorio;
import com.nqn.apideservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNomeDeUsuario(String nomeDeUsuario);


}
