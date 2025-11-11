package com.nqn.apideservico.service;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.model.Relatorio;
import com.nqn.apideservico.repository.RelatorioRepository;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    @Autowired
    private RelatorioRepository repository;

    @Override
    public RelatorioResponseDTO salvar(@RequestBody RelatoriORequestDTO dto) {
        Relatorio relatorio = new Relatorio(dto);
        relatorio.setDataDoServico(LocalDate.now());
        Relatorio relatorioSalvo = repository.save(relatorio);
        return new RelatorioResponseDTO(relatorioSalvo.getId(), relatorioSalvo.getDataDoServico(), relatorioSalvo.getPlacaDaViatura(), relatorioSalvo.getKmInicial(),
                relatorioSalvo.getKmFinal(), relatorioSalvo.getAvarias(), relatorioSalvo.isAbastecida());
    }

    @Override
    public RelatorioResponseDTO buscarRelatorioPorID(String id) {
        Relatorio relatorio = repository.findById(id).orElse(null);
        return new RelatorioResponseDTO(relatorio.getId(), relatorio.getDataDoServico(), relatorio.getPlacaDaViatura(), relatorio.getKmInicial(),
                relatorio.getKmFinal(), relatorio.getAvarias(), relatorio.isAbastecida());
    }

    @Override
    public RelatorioResponseDTO buscarRelatorioPorData(LocalDate dataDoServico) {
        Relatorio relatorio = repository.findByDataDoServico(dataDoServico);
        RelatorioResponseDTO relatorioDTO = new RelatorioResponseDTO(relatorio.getId(), relatorio.getDataDoServico(), relatorio.getPlacaDaViatura(), relatorio.getKmInicial(),
                relatorio.getKmFinal(), relatorio.getAvarias(), relatorio.isAbastecida());
        return relatorioDTO;
    }

    @Override
    public List<RelatorioResponseDTO> buscarRelatoriosOrdenadosPorData() {
        List<Relatorio> relatorios = repository.findAllByOrderByDataDoServico();
        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .map(r -> new RelatorioResponseDTO(r.getId(), r.getDataDoServico(), r.getPlacaDaViatura(), r.getKmInicial(),
                        r.getKmFinal(), r.getAvarias(), r.isAbastecida()))
                .collect(Collectors.toList());
        return relatoriosDTO;
    }

    @Override
    public List<RelatorioResponseDTO> buscarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal) {
        List<Relatorio> relatorios = repository.findAllByDataDoServicoBetween(dataInicial, dataFinal);
        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .sorted(Comparator.comparing(Relatorio::getDataDoServico))
                .map(r -> new RelatorioResponseDTO(
                        r.getId(),
                        r.getDataDoServico(),
                        r.getPlacaDaViatura(),
                        r.getKmInicial(),
                        r.getKmFinal(),
                        r.getAvarias(),
                        r.isAbastecida()))
                .collect(Collectors.toList());
        return relatoriosDTO;
    }

    @Override
    public void deletarRelatorioPorId(String id) {
        repository.deleteById(id);
    }

    @Override
    public RelatorioResponseDTO alterarRelatorioPorId(String id, RelatoriORequestDTO dtoAlterado) {
        Relatorio relatorioAlterado = new Relatorio(dtoAlterado);
        relatorioAlterado.setId(id);
        RelatorioResponseDTO resposta = new RelatorioResponseDTO(relatorioAlterado.getId(), relatorioAlterado.getDataDoServico(), relatorioAlterado.getPlacaDaViatura(), relatorioAlterado.getKmInicial(),
                relatorioAlterado.getKmFinal(), relatorioAlterado.getAvarias(), relatorioAlterado.isAbastecida());
        return resposta;
    }
}


