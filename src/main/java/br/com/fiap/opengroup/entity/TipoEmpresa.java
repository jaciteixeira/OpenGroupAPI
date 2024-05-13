package br.com.fiap.opengroup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEmpresa {
    ME(1L, "Microempresa", "ME"),
    LTDA(2L, "Sociedade Limitada", "LTDA"),
    SA(3L, "Sociedade Anônima", "SA"),
    EIRELI(4L, "Empresa Individual de Responsabilidade Limitada", "EIRELI"),
    EI(5L, "Empresário Individual", "EI"),
    EPP(6L, "Empresa de Pequeno Porte", "EPP"),
    MEI(7L, "Microempreendedor Individual", "MEI");

    private Long id;
    private String nome;
    private String sigla;

}
