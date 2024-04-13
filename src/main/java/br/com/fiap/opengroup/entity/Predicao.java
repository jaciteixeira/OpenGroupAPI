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
@Table(name = "OG_TB_PREDICAO")
public class Predicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PREDICAO")
    @SequenceGenerator(name = "SQ_PREDICAO", sequenceName = "SQ_PREDICAO", allocationSize = 1)
    @Column(name = "ID_PREDICAO")
    private Long id;

    @Column(name = "RS_PREDICAO")
    private String resultado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "FK_PREDICAO_CLIENTE"
            )
    )
    private Cliente dadosCliente;

}
