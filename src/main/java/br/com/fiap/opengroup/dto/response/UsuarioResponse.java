package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(
        String nome,
        String identificacao,
        String status
) {
}
