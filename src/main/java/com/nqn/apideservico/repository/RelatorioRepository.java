package com.nqn.apideservico.repository;

import com.nqn.apideservico.model.Relatorio;
import com.nqn.apideservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioRepository extends JpaRepository<Relatorio, String> {

    Relatorio findByIdAndNomeDeUsuario(String id, Usuario usuario);

    List<Relatorio> findAllByOrderByDataDoServicoAndNomeDeUsuario(Usuario usuario);

    Relatorio findAllByDataDoServicoAndNomeDeUsuario(LocalDate dataDoServico, Usuario usuario);

    List<Relatorio> findAllByDataDoServicoBetweenAndNomeDeUsuario(LocalDate dataDoServico, LocalDate dataFinal, Usuario usuario);

    Relatorio findByDataDoServicoAndNomeDeUsuario(LocalDate dataDoServico, Usuario usuario);
}
