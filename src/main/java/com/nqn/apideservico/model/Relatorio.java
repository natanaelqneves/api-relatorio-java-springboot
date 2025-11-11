package com.nqn.apideservico.model;

import com.nqn.apideservico.dto.RelatoriORequestDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate dataDoServico;
    @Column(nullable = false)
    private String placaDaViatura;
    @Column(nullable = false)
    private Integer kmInicial;
    private Integer kmFinal;
    private String avarias;
    private Boolean abastecida;

    public Relatorio() {
    }

    public Relatorio(String id, LocalDate dataDoServico, String placaDaViatura, Integer kmInicial, Integer kmFinal, String avarias, Boolean abastecida) {
        this.id = id;
        this.dataDoServico = dataDoServico;
        this.placaDaViatura = placaDaViatura;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.avarias = avarias;
        this.abastecida = abastecida;
    }

    public Relatorio(RelatoriORequestDTO relatorioDTO) {
        this.id = id;
        this.dataDoServico = relatorioDTO.dataDoServico();
        this.placaDaViatura = relatorioDTO.placaDaViatura();
        this.kmInicial = relatorioDTO.kmInicial();
        this.kmFinal = relatorioDTO.kmFinal();
        this.avarias = relatorioDTO.avarias();
        this.abastecida = relatorioDTO.abastecida();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataDoServico() {
        return dataDoServico;
    }

    public void setDataDoServico(LocalDate dataDoServico) {
        this.dataDoServico = dataDoServico;
    }

    public String getPlacaDaViatura() {
        return placaDaViatura;
    }

    public void setPlacaDaViatura(String placaDaViatura) {
        this.placaDaViatura = placaDaViatura;
    }

    public Integer getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Integer getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getAvarias() {
        return avarias;
    }

    public void setAvarias(String avarias) {
        this.avarias = avarias;
    }

    public Boolean isAbastecida() {
        return abastecida;
    }

    public void setAbastecida(Boolean abastecida) {
        this.abastecida = abastecida;
    }
}
