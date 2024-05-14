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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OG_ARQUIVO")
    @SequenceGenerator(name = "SQ_OG_ARQUIVO", sequenceName = "SQ_OG_ARQUIVO", allocationSize = 1)
    @Column(name = "ID_ARQUIVO")
    private Long id;

    @Column(name = "NM_ARQUIVO")
    private String nome;

    @Column(name = "CAMINHO_ARQUIVO")
    private String caminho;

    @Column(name = "EXTENCAO_ARQUIVO")
    private String extensao;

    @Column(name = "TAMANHO")
    private Long tamanho;

    @Column(name = "PALAVRA_CHAVE")
    private String palavrasChave;

    @Column(name = "DT_ARQUIVO")
    private LocalDate dataUpload;

    @Column(name = "RESUMO")
    private String resumo;
}