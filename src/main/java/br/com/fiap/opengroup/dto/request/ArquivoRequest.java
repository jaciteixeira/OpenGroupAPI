package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ArquivoRequest(
        @NotBlank(message = "O nome do arquivo é obrigatório!")
        String nome,
        @NotNull(message = "O tipo do arquivo é obrigatório!")
        String tipo,
        @NotNull(message = "O tamanho do arquivo é obrigatório!")
        Long tamanho,
        String palavrasChave,
        @NotNull(message = "O link do arquivo é obrigatório!")
        String link,
        LocalDate dataUpload,
        String resumo,
        @NotNull(message = "O id dos dados que o arquivo se refere é obrigatório!")
        Long dadosClienteId
) {
}
