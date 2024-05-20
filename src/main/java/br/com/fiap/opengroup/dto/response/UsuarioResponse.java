package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(
        String identificacao,
        String status,
        String email,
        Long clienteId

) {
}
