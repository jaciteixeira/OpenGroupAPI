package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

@Builder
public record ArquivoResponse(
        Long id,
        String nome,
        String caminho,
        String cliente
) {
}
