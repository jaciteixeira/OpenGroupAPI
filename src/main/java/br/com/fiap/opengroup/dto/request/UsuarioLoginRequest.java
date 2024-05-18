package br.com.fiap.opengroup.dto.request;

public record UsuarioLoginRequest(
        String email,
        String identificacao,
        String senha
) {
}
