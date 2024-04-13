package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

@Builder
public record ItemResponse(
        Long id,
        String nome,
        String descricao
) {
}
