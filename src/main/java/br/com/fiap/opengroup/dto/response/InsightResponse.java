package br.com.fiap.opengroup.dto.response;

import br.com.fiap.opengroup.entity.Impacto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Builder
public record InsightResponse(
        String  cliente,
        LocalDate dataGeracao,
        String detalhes,
        String recomendacoes,
        Impacto impacto
) {
}
