package br.com.fiap.opengroup.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ClienteRequest(
        @Size(min = 5, max = 255)
        @NotNull(message = "Nome é obrigatório!")
        String nome,
        @Email(message = "Email inválido!")
        @NotNull(message = "Email é obrigatório!")
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
        LocalDate dataNascimento,
        String genero,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
        @NotNull(message = "CEP é obrigatório!")
        String cep,
        String telefone,
        //@CPF(message = "CPF inválido")
        @NotNull(message = "CPF é obrigatório!")
        String cpf,
        String profissao,
        String estadoCivil,
        LocalDate ultimaCompra
) {
}
