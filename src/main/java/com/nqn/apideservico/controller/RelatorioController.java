package com.nqn.apideservico.controller;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.service.RelatorioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioServiceImpl relatorioService;

    @PostMapping
    public ResponseEntity<RelatorioResponseDTO> salvar(@RequestBody RelatoriORequestDTO dto){
        RelatorioResponseDTO resposta = relatorioService.salvar(dto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponseDTO>> buscarRelatorios(){
        List<RelatorioResponseDTO> resposta = relatorioService.buscarRelatoriosOrdenadosPorData();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarRelatorioPorId(@PathVariable String id){
        RelatorioResponseDTO resposta = relatorioService.buscarRelatorioPorID(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("{id}")
    public ResponseEntity<RelatorioResponseDTO> alterarRelatorioPorId(@PathVariable("id") String id, @RequestBody RelatoriORequestDTO dto){
        RelatorioResponseDTO resposta = relatorioService.alterarRelatorioPorId(id, dto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("data/{dataDoServico}")
    public ResponseEntity<RelatorioResponseDTO> buscarRelatorioPorDataDoServico(@PathVariable("dataDoServico") LocalDate dataDoServico){
        RelatorioResponseDTO resposta = relatorioService.buscarRelatorioPorData(dataDoServico);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("data/{dataInicial}/{dataFinal}")
    public List<RelatorioResponseDTO> buscarRelatorioPorDataDoServico(@PathVariable("dataInicial") LocalDate dataInicial, @PathVariable("dataFinal") LocalDate dataFinal){
        List<RelatorioResponseDTO> resposta =  relatorioService.buscarPorIntervaloDeDatas(dataInicial, dataFinal);
        return ResponseEntity.ok(resposta).getBody();
    }
}
