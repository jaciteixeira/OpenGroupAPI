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
@Table(name = "OG_TB_ITEM_RECOMENDADO")
public class ItemRecomendado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM_RECOMENDADO")
    @SequenceGenerator(name = "SQ_ITEM_RECOMENDADO", sequenceName = "SQ_ITEM_RECOMENDADO", allocationSize = 1)
    @Column(name = "ID_ITEM_RECOMENDADO")
    private Long id;

    @Column(name = "NM_ITEM")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;
}