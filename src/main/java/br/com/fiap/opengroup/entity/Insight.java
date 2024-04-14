package br.com.fiap.opengroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OG_TB_INSIGHT")
public class Insight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INSIGH")
    @SequenceGenerator(name = "SQ_INSIGH", sequenceName = "SQ_INSIGH", allocationSize = 1)
    @Column(name = "ID_INSIGH")
    private Long id;

    @Column(name = "DATA GERACAO")
    private LocalDate dataGeracao;

    @Column(name = "DETALHES")
    private String detalhes;

    @Column(name = "RECOMENDACOES")
    private String recomendacoes;

    @Column(name = "IMPACTO")
    private Double impacto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "DADOS_INSIGHT",
            referencedColumnName = "ID_DADOS",
            foreignKey = @ForeignKey(
                    name = "FK_INSIGH_DADOS"
            )
    )
    private DadosCliente dados;



}
