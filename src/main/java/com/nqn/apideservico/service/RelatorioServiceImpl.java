package com.nqn.apideservico.service;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.exceptions.ResourceNotFoundException;
import com.nqn.apideservico.model.Relatorio;
import com.nqn.apideservico.model.Usuario;
import com.nqn.apideservico.repository.RelatorioRepository;
import com.nqn.apideservico.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    private RelatorioRepository relatorioRepository;
    private UsuarioRepository usuarioRepository;

    public RelatorioServiceImpl(RelatorioRepository relatorioRepository, UsuarioRepository usuarioRepository) {
        this.relatorioRepository = relatorioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public RelatorioResponseDTO salvar(RelatoriORequestDTO dto) {
        Relatorio relatorio = new Relatorio(dto);
        relatorio.setUsuario(buscarUsuarioLogado());
        Relatorio relatorioSalvo = relatorioRepository.save(relatorio);

        return toRelatorioResponseDTO(relatorioSalvo);
    }

    @Override
    public RelatorioResponseDTO buscarRelatorioPorID(String id) {
        Relatorio relatorio = relatorioRepository.findByIdAndNomeDeUsuario(id, buscarUsuarioLogado());

        if(relatorio == null) {
            throw new ResourceNotFoundException("Relatório não encontrado com ID: " + id);
        }

        return toRelatorioResponseDTO(relatorio);
    }

    @Override
    public List<RelatorioResponseDTO> buscarRelatoriosOrdenadosPorData() {
        List<Relatorio> relatorios = relatorioRepository.findAllByOrderByDataDoServicoAndNomeDeUsuario(buscarUsuarioLogado());
        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .map(this::toRelatorioResponseDTO)
                .collect(Collectors.toList());

        return relatoriosDTO;
    }

    @Override
    public RelatorioResponseDTO buscarRelatorioPorData(LocalDate dataDoServico) {
        Relatorio relatorio = relatorioRepository.findByDataDoServicoAndNomeDeUsuario(dataDoServico, buscarUsuarioLogado());

        if(relatorio == null) {
            throw new ResourceNotFoundException("Relatório não encontrado para a data: " + dataDoServico);
        }

        return toRelatorioResponseDTO(relatorio);
    }



    @Override
    public List<RelatorioResponseDTO> buscarPorIntervaloDeDatas(
            LocalDate dataInicial,
            LocalDate dataFinal) {

        List<Relatorio> relatorios = relatorioRepository.findAllByDataDoServicoBetweenAndNomeDeUsuario(dataInicial, dataFinal, buscarUsuarioLogado());

        if(relatorios == null) {
            throw new ResourceNotFoundException("Não há relatórios para o período especificado: " + dataInicial + " à " + dataFinal + ".");
        }

        List<RelatorioResponseDTO> relatoriosDTO = relatorios.stream()
                .sorted(Comparator.comparing(Relatorio::getDataDoServico))
                .map(this::toRelatorioResponseDTO)
                .collect(Collectors.toList());



        return relatoriosDTO;
    }



    @Override
    public RelatorioResponseDTO alterarRelatorioPorId(String id, RelatoriORequestDTO dtoAlterado) {
        Relatorio relatorio = relatorioRepository.findByIdAndNomeDeUsuario(id, buscarUsuarioLogado());
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

    @Override
    public void deletarRelatorioPorId(String id) {
        Relatorio relatorio = relatorioRepository.findByIdAndNomeDeUsuario(id, buscarUsuarioLogado());
        if(relatorio == null) {
            throw new ResourceNotFoundException("Não foi possível excluir. Relatorio não encontrado com ID: " + id);
        }

        relatorioRepository.deleteById(id);
    }

    private Usuario buscarUsuarioLogado(){
        String nomeDeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByNomeDeUsuario(nomeDeUsuario);
        if(usuario == null) {
            throw new ResourceNotFoundException("Usuário logado não encontrado.");
        }
        return usuario;
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