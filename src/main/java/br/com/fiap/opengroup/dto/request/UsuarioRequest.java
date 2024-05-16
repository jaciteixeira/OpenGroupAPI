package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(
//        @NotBlank(message = "O src é obrigatório!")
//        String nome,
        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "Email inválido!")
        String email,
        @NotBlank
        String identificacao,
        String telefone,
        @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}")
        @NotBlank
        @NotNull(message = "a senha é obrigatório!")
        String senha,
        @NotBlank
        String status,
        @NotNull(message = "O id cliente que o usuario se reporta é obrigatório!")
        Long clienteId
) {}
