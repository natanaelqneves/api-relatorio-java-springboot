package com.nqn.apideservico.dto;

import java.time.LocalDate;

public record RelatorioResponseDTO(String id,
                                   LocalDate dataDoServico,
                                   String placaDaViatura,
                                   Integer kmInicial,
                                   Integer kmFinal,
                                   String avarias,
                                   Boolean abastecida) {
}
