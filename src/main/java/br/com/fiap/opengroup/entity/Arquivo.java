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
@Table(name = "OG_TB_ARQUIVO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME", columnNames = "NM_ARQUIVO")
})
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OG_ARQUIVO")
    @SequenceGenerator(name = "SQ_OG_ARQUIVO", sequenceName = "SQ_OG_ARQUIVO", allocationSize = 1)
    @Column(name = "ID_ARQUIVO")
    private Long id;

    @Column(name = "NM_ARQUIVO")
    private String nome;

    @Column(name = "SRC")
    private String src;

    @Column(name = "CAMINHO")
    private String caminho;

    @Column(name = "EXTENCAO_ARQUIVO")
    private String extensao;

    @Column(name = "TAMANHO")
    private Long tamanho;

    @Column(name = "PALAVRA_CHAVE")
    private String palavrasChave;

    @Column(name = "DT_ARQUIVO")
    private LocalDateTime dataUpload;

    @Column(name = "RESUMO")
    private String resumo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(name = "FK_CLIENTE_ARQUIVO")
    )
    private Cliente cliente;
}