package com.nqn.apideservico.service;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.model.Relatorio;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioService {

    public RelatorioResponseDTO salvar(RelatoriORequestDTO relatoriORequestDTO);

    public RelatorioResponseDTO buscarRelatorioPorID(String id);

    public RelatorioResponseDTO buscarRelatorioPorData(LocalDate dataDoServico);

    public List<RelatorioResponseDTO> buscarRelatoriosOrdenadosPorData();

    public List<RelatorioResponseDTO> buscarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal);

    public void deletarRelatorioPorId(String id);

    public RelatorioResponseDTO alterarRelatorioPorId(String id,  RelatoriORequestDTO relatorio);
}
