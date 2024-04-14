package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ArquivoResponse(
        Long id,
        String nome,
        String tipo,
        String link,
        Long tamanho,
        String resumo,
        String dadosCliente
) {
}
