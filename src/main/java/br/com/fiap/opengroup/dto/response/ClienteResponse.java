package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

@Builder
public record ClienteResponse(
        Long id,
        String nome,
        String segmento,
        String localizacao,
        Integer tempoAtuacao,
        Integer numFuncionarios,
        String canaisVenda,
        String produtosServicos,
        String tipo,
        String porte
) {
}
