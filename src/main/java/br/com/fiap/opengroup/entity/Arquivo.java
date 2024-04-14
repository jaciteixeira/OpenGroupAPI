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
@Table(name = "OG_TB_ARQUIVO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NOME", columnNames = "NM_ARQUIVO")
})
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARQUIVO")
    @SequenceGenerator(name = "SQ_ARQUIVO", sequenceName = "SQ_ARQUIVO", allocationSize = 1)
    @Column(name = "ID_ARQUIVO")
    private Long id;

    @Column(name = "NM_ARQUIVO")
    private String nome;

    @Column(name = "TIPO_ARQUIVO")
    private String tipo;

    @Column(name = "TAMANHO")
    private Long tamanho;

    @Column(name = "PALAVRA_CHAVE")
    private String palavrasChave;

    @Column(name = "LINK_ARQUIVO")
    private String link; // O caminho do arquivo

    @Column(name = "DT_ARQUIVO")
    private LocalDate dataUpload;

    @Column(name = "RESUMO")
    private String resumo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_DADOS",
            referencedColumnName = "ID_DADOS",
            foreignKey = @ForeignKey(
                    name = "FK_ARQUIVO_DADOS"
            )
    )
    private DadosCliente dadosCliente;
}