package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequest(
        @NotNull(message = "Nome é obrigatório!")
        String nome,
        String descricao
) {
}
