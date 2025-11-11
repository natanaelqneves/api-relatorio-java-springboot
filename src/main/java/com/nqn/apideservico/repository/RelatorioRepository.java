package com.nqn.apideservico.repository;

import com.nqn.apideservico.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioRepository extends JpaRepository<Relatorio, String> {
    List<Relatorio> findAllByOrderByDataDoServico();

    Relatorio findAllByDataDoServico(LocalDate dataDoServico);

    List<Relatorio> findAllByDataDoServicoBetween(LocalDate dataDoServico, LocalDate dataFinal);

    Relatorio findByDataDoServico(LocalDate dataDoServico);
}
