package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DadosRequest(
        @NotBlank(message = "O nome do arquivo é obrigatório!")
        String nome,
        String segmento,
        String localizacao,
        Integer tempoAtuacao,
        Integer numFuncionarios,
        Double faturamentoAnual,
        String canaisVenda,
        String tipo,
        String porte,
       String desafios
) {
}
