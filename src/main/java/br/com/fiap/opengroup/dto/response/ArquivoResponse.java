package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArquivoResponse(
        Long id,
        String nome,
        String src,
        String caminho,
        String extensao,
        Long tamanho,
        String resumo,
        LocalDateTime dataUpload
) {
}
