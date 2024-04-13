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
@Table(name = "OG_TB_ARQUIVO")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARQUIVO")
    @SequenceGenerator(name = "SQ_ARQUIVO", sequenceName = "SQ_ARQUIVO", allocationSize = 1)
    @Column(name = "ID_ARQUIVO")
    private Long id;

    @Column(name = "NM_ARQUIVO")
    private String nome;

    @Column(name = "CM_ARQUIVO")
    private String caminho; // O caminho do arquivo (URL)

    @Column(name = "DT_ARQUIVO")
    private LocalDate dataArquivo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "" +
                    "",
            foreignKey = @ForeignKey(
                    name = "FK_ARQUIVO_CLIENTE"
            )
    )
    private Cliente dadosCliente;
}