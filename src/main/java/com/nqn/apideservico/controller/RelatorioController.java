package com.nqn.apideservico.controller;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import com.nqn.apideservico.dto.RelatorioResponseDTO;
import com.nqn.apideservico.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @PostMapping
    public ResponseEntity<RelatorioResponseDTO> salvar(@Valid @RequestBody RelatoriORequestDTO dto){
        RelatorioResponseDTO resposta = relatorioService.salvar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resposta.id())
                .toUri();

        return ResponseEntity.created(location).body(resposta);
    }

    @GetMapping("{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarRelatorioPorId(@PathVariable String id){
        RelatorioResponseDTO resposta = relatorioService.buscarRelatorioPorID(id);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponseDTO>> buscarRelatorios(){
        List<RelatorioResponseDTO> resposta = relatorioService.buscarRelatoriosOrdenadosPorData();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("data/{dataDoServico}")
    public ResponseEntity<RelatorioResponseDTO> buscarRelatorioPorDataDoServico(@PathVariable("dataDoServico") LocalDate dataDoServico){
        RelatorioResponseDTO resposta = relatorioService.buscarRelatorioPorData(dataDoServico);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("data")
    public ResponseEntity<List<RelatorioResponseDTO>> buscarRelatorioPorDataDoServico(
            @RequestParam(value = "dataInicial", required = false) LocalDate dataInicial,
            @RequestParam(value = "dataInicial", required = false) LocalDate dataFinal){

            if(dataInicial != null && dataFinal != null){
                List<RelatorioResponseDTO> resposta =  relatorioService.buscarPorIntervaloDeDatas(dataInicial, dataFinal);
                return ResponseEntity.ok(resposta);
            }
        List<RelatorioResponseDTO> resposta = relatorioService.buscarRelatoriosOrdenadosPorData();
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("{id}")
    public ResponseEntity<RelatorioResponseDTO> alterarRelatorioPorId(@PathVariable("id") String id, @Valid @RequestBody RelatoriORequestDTO dto){
        RelatorioResponseDTO resposta = relatorioService.alterarRelatorioPorId(id, dto);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarRelatorioPorId(@PathVariable String id) {
        relatorioService.deletarRelatorioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
