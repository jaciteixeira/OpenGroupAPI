package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DadosResponse(
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
