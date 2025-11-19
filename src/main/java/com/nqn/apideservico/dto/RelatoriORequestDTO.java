package com.nqn.apideservico.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RelatoriORequestDTO(
                                @NotNull(message = "A data do serviço é obrigatória.")
                                LocalDate dataDoServico,

                                @NotBlank(message = "A placa da viatura não pode estar em branco.") // Para Strings
                                String placaDaViatura,

                                @NotNull(message = "O KM inicial é obrigatório.") // Para tipos Wrapper (Integer)
                                @Min(value = 1, message = "O KM inicial deve ser maior que zero.")
                                Integer kmInicial,
                                Integer kmFinal,
                                String avarias,
                                Boolean abastecida) {
}
