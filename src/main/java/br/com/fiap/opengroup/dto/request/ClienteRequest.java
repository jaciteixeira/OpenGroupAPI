package br.com.fiap.opengroup.dto.request;

import br.com.fiap.opengroup.entity.TipoEmpresa;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank(message = "O src do arquivo é obrigatório!")
        String nome,
        String segmento,
        String localizacao,
        Integer tempoAtuacao,
        Integer numFuncionarios,
        Double faturamentoAnual,
        String canaisVenda,
        TipoEmpresa tipo,
        String porte,
       String desafios
) {
}
