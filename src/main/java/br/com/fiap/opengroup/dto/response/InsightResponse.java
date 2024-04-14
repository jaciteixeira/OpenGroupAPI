package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record InsightResponse(
        String  dadosId,
        LocalDate dataGeracao,
        String detalhes,
        String recomendacoes,
        Double impacto
) {
}
