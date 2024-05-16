package br.com.fiap.opengroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OG_TB_INSIGHT")
public class Insight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OG_INSIGHT")
    @SequenceGenerator(name = "SQ_OG_INSIGHT", sequenceName = "SQ_OG_INSIGHT", allocationSize = 1)
    @Column(name = "ID_INSIGH")
    private Long id;

    @Column(name = "DATA_GERACAO")
    private LocalDateTime dataGeracao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "IMPACTO")
    private Impacto impacto;

    @Column(name = "DETALHES")
    private String detalhes;

    @Column(name = "RECOMENDACOES")
    private String recomendacoes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "FK_INSIGHT_CLIENTE"
            )
    )
    private Cliente cliente;



}
