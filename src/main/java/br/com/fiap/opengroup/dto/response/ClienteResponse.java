package br.com.fiap.opengroup.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteResponse(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String genero,
        String profissao
) {
}
