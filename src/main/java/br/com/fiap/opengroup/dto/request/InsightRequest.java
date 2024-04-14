package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record InsightRequest(
        @NotNull(message = "A data de geração é obrigatória!")
        LocalDate dataGeracao,
        @NotBlank(message = "Os detalhes são obrigatórios!")
        String detalhes,
        @NotBlank(message = "As recomendações são obrigatórias!")
        String recomendacoes,
        @NotNull(message = "O impacto é obrigatório!")
        Double impacto,
        @NotNull(message = "O id dos dados que o insight se refere é obrigatório!")
        Long dadosId
) {}
