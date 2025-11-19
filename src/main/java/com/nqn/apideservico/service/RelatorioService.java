package com.nqn.apideservico.service;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioService {

    RelatorioResponseDTO salvar(RelatoriORequestDTO relatoriORequestDTO);

    RelatorioResponseDTO buscarRelatorioPorID(String id);

    RelatorioResponseDTO buscarRelatorioPorData(LocalDate dataDoServico);

    List<RelatorioResponseDTO> buscarRelatoriosOrdenadosPorData();

    List<RelatorioResponseDTO> buscarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal);

    void deletarRelatorioPorId(String id);

    RelatorioResponseDTO alterarRelatorioPorId(String id,  RelatoriORequestDTO relatorio);


}
