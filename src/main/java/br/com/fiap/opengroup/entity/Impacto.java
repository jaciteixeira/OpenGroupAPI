package br.com.fiap.opengroup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Impacto {
    BAIXO(1L, 1, "BAIXO"),
    MÉDIO(2L, 2, "MÉDIO"),
    ALTO(3L, 3, "ALTO"),
    MUITO_ALTO(4L, 4, "MUITO ALTO"),
    EXTREMO(5L, 5, "EXTREMO");

    private Long id;
    private Integer valor;
    private String nome;

}

