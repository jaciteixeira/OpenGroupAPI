package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ArquivoRequest(
        @NotBlank(message = "Nome é obrigatório!")
        @NotNull(message = "Nome é obrigatório!")
        String nome,

        @NotNull(message = "Caminho é obrigatório!")
        String caminho,

        LocalDate dataArquivo,

        //@NotNull(message = "ID do cliente é obrigatório!")
         Long cliente
) {
}
