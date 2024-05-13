package br.com.fiap.opengroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OG_TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME", columnNames = "NM_CLIENTE")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_CLIENTE")
    private String nome;

    @Column(name = "SEGMENTO")
    private String segmento;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @Column(name = "TEMPO_ATUACAO")
    private Integer tempoAtuacao;

    @Column(name = "NUM_FUNCIONARIOS")
    private Integer numFuncionarios;

    @Column(name = "FATURAMENTO_ANUAL")
    private Double faturamentoAnual;

    @Column(name = "CANAIS_VENDA")
    private String canaisVenda;

    @Column(name = "PRODUTOS_SERVICOS")
    private String produtosServicos;

    @Column(name = "TIPO")
    private TipoEmpresa tipo;

    @Column(name = "PORTE")
    private String porte;

    @Column(name = "CONCORRENTES")
    private String concorrentes;

    @Column(name = "DESAFIOS")
    private String desafios;

    @Column(name = "OBJETIVOS")
    private String objetivos;
}
