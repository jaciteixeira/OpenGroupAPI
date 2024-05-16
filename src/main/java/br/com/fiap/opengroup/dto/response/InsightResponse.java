package br.com.fiap.opengroup.dto.response;

import br.com.fiap.opengroup.entity.Impacto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InsightResponse(
        String  cliente,
        LocalDateTime dataGeracao,
        String detalhes,
        String recomendacoes,
        Impacto impacto
) {
}
