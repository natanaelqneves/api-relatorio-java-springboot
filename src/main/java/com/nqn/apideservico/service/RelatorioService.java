package com.nqn.apideservico.service;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.exceptions.ResourceNotFoundException;
import com.nqn.apideservico.model.Relatorio;
import com.nqn.apideservico.repository.RelatorioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public RelatorioResponseDTO salvar(RelatoriORequestDTO dto) {
        Relatorio relatorio = new Relatorio(dto);
        Relatorio relatorioSalvo = relatorioRepository.save(relatorio);

        return toRelatorioResponseDTO(relatorioSalvo);
    }


    public RelatorioResponseDTO buscarRelatorioPorID(String id) {
        Relatorio relatorio = relatorioRepository.findById(id).orElse(null);

        if(relatorio == null) {
            throw new ResourceNotFoundException("Relatório não encontrado com ID: " + id);
        }

        return toRelatorioResponseDTO(relatorio);
    }


    public List<RelatorioResponseDTO> buscarRelatoriosOrdenadosPorData() {
        List<Relatorio> relatorios = relatorioRepository.findAllByOrderByDataDoServico();
        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .map(this::toRelatorioResponseDTO)
                .collect(Collectors.toList());

        return relatoriosDTO;
    }


    public RelatorioResponseDTO buscarRelatorioPorData(LocalDate dataDoServico) {
        Relatorio relatorio = relatorioRepository.findByDataDoServico(dataDoServico);

        if(relatorio == null) {
            throw new ResourceNotFoundException("Relatório não encontrado para a data: " + dataDoServico);
        }

        return toRelatorioResponseDTO(relatorio);
    }




    public List<RelatorioResponseDTO> buscarPorIntervaloDeDatas(
            LocalDate dataInicial,
            LocalDate dataFinal) {

        List<Relatorio> relatorios = relatorioRepository.findAllByDataDoServicoBetween(dataInicial, dataFinal);

        if(relatorios == null) {
            throw new ResourceNotFoundException("Não há relatórios para o período especificado: " + dataInicial + " à " + dataFinal + ".");
        }

        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .sorted(Comparator.comparing(Relatorio::getDataDoServico))
                .map(this::toRelatorioResponseDTO)
                .collect(Collectors.toList());



        return relatoriosDTO;
    }




    public RelatorioResponseDTO alterarRelatorioPorId(String id, RelatoriORequestDTO dtoAlterado) {
        Relatorio relatorio = relatorioRepository.findById(id).orElse(null);
        if(relatorio == null) {
            throw new ResourceNotFoundException("Não foi possível alterar. Relatório Não encontrado com ID: " + id);
        }


        relatorio.setDataDoServico(dtoAlterado.dataDoServico());
        relatorio.setPlacaDaViatura(dtoAlterado.placaDaViatura());
        relatorio.setKmInicial(dtoAlterado.kmInicial());
        relatorio.setKmFinal(dtoAlterado.kmFinal());
        relatorio.setAvarias(dtoAlterado.avarias());
        relatorio.setAbastecida(dtoAlterado.abastecida());

        Relatorio relatorioSalvo = relatorioRepository.save(relatorio);

        return toRelatorioResponseDTO(relatorioSalvo);
    }


    public void deletarRelatorioPorId(String id) {
        Relatorio relatorio = relatorioRepository.findById(id).orElse(null);
        if(relatorio == null) {
            throw new ResourceNotFoundException("Não foi possível excluir. Relatorio não encontrado com ID: " + id);
        }

        relatorioRepository.deleteById(id);
    }



    private RelatorioResponseDTO toRelatorioResponseDTO(Relatorio relatorio) {
        return new RelatorioResponseDTO(
                relatorio.getId(),
                relatorio.getDataDoServico(),
                relatorio.getPlacaDaViatura(),
                relatorio.getKmInicial(),
                relatorio.getKmFinal(),
                relatorio.getAvarias(),
                relatorio.isAbastecida());
    }
}